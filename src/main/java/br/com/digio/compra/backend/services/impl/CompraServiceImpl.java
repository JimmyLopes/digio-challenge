package br.com.digio.compra.backend.services.impl;

import br.com.digio.compra.backend.domain.Cliente;
import br.com.digio.compra.backend.domain.Compra;
import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.dto.NovaCompraDTO;
import br.com.digio.compra.backend.exceptions.BusinessException;
import br.com.digio.compra.backend.repository.CompraRepository;
import br.com.digio.compra.backend.services.ClienteService;
import br.com.digio.compra.backend.services.CompraService;
import br.com.digio.compra.backend.services.ProdutoService;
import br.com.digio.compra.backend.utils.CollectionUtils;
import br.com.digio.compra.backend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.compraRepository = compraRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @Override
    public Compra salvarCompra(@Valid NovaCompraDTO novaCompraDTO, String idCliente) {
        Compra compra = new Compra();
        compra.setCliente(clienteService.buscarClientePorId(idCliente));
        compra.setProdutos(injetarItensNaCompra(novaCompraDTO));
        verificarMaioridadeParaProdutos(compra.getCliente(), compra.getProdutos());
        compra.setDataCompra(LocalDate.now());
        compra.setValorCompra(determinarValorCompra(compra.getProdutos(), false));
        compra.setValorCompraComDesconto(determinarValorCompra(compra.getProdutos(), true));

        return compraRepository.save(compra);
    }

    @Override
    public List<Compra> buscarCompras(String cpf, String dataCompra) {
        LocalDate data = getLocalDateFromString(dataCompra);
        validaParametrosDeCompra(cpf, data);
        List<Compra> compras = new ArrayList<>();
        if (cpf != null) compras = this.buscarComprasClientePorCpf(cpf);
        if (dataCompra != null) compras = this.buscarComprasPorData(data);
        return compras;
    }

    private LocalDate getLocalDateFromString(String dataCompra) {
        try {
            return LocalDate.parse(dataCompra, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException exception) {
            throw new BusinessException(StringUtils.buscarMensagemDeValidacao("compra.data.formato.invalido", dataCompra));
        }
    }

    private List<Produto> injetarItensNaCompra(NovaCompraDTO novaCompraDTO) {
        List<Produto> produtos = new ArrayList<>();

        novaCompraDTO.getProdutos().forEach(prod -> {
            Produto produto = produtoService.buscarProdutoPorId(prod.getIdProduto());
            produtos.addAll(addItemXVezes(produto, prod.getQuantidade()));
        });

        return produtos;
    }

    private Collection<? extends Produto> addItemXVezes(Produto produto, Integer quantidade) {
        List<Produto> produtos = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            produtos.add(produto);
        }
        return produtos;
    }

    private void verificarMaioridadeParaProdutos(Cliente cliente, List<Produto> produtos) {
        produtos.forEach(produto -> {
            if (produto.getIsMaioridade() && !vericarClienteMaioridade(cliente)) {
                throw new BusinessException(StringUtils.buscarMensagemDeValidacao("compra.menoridade"));
            }
        });
    }

    private boolean vericarClienteMaioridade(Cliente cliente) {
        return cliente.getIdade() > 18;
    }

    private Double determinarValorCompra(List<Produto> produtos, boolean aplicarDesconto) {
        return produtos.stream().mapToDouble(produto -> produto.getValor() * (aplicarDesconto ? produto.getCategoria().getDesconto() : 1)).sum();
    }

    private void validaParametrosDeCompra(String cpf, LocalDate dataCompra) {
        if (cpf != null && dataCompra != null)
            throw new BusinessException(StringUtils.buscarMensagemDeValidacao("cpf.data.compra.preenchido"));
        if (cpf == null && dataCompra == null)
            throw new BusinessException(StringUtils.buscarMensagemDeValidacao("cpf.data.compra.nao.preenchido"));
    }

    public List<Compra> buscarComprasClientePorCpf(String cpf) {
        return buscarTodasCompras().stream().filter(compra -> compra.getCliente().getCpf().equals(cpf)).collect(Collectors.toList());
    }

    public List<Compra> buscarComprasPorData(LocalDate dataCompra) {
        return buscarTodasCompras().stream().filter(compra -> compra.getDataCompra().equals(dataCompra)).collect(Collectors.toList());
    }

    public List<Compra> buscarTodasCompras() {
        return CollectionUtils.getListFromIterable(compraRepository.findAll());
    }
}
