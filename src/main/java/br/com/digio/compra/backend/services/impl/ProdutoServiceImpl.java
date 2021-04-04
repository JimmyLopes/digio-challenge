package br.com.digio.compra.backend.services.impl;

import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.dto.NovoProdutoDTO;
import br.com.digio.compra.backend.dto.NovoValorProdutoDTO;
import br.com.digio.compra.backend.exceptions.ResourceNotFoundException;
import br.com.digio.compra.backend.repository.ProdutoRepository;
import br.com.digio.compra.backend.services.ProdutoService;
import br.com.digio.compra.backend.utils.CollectionUtils;
import br.com.digio.compra.backend.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ModelMapper mapper) {
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    @Override
    public Produto salvarProduto(@Valid NovoProdutoDTO novoProdutoDTO) {
        return produtoRepository.save(mapper.map(novoProdutoDTO, Produto.class));
    }

    @Override
    public void excluirProduto(String idProduto) {
        Produto produto = buscarProdutoPorId(idProduto);
        produtoRepository.delete(produto);
    }

    @Override
    public Produto alterarValorProduto(NovoValorProdutoDTO novoValorProdutoDTO, String idProduto) {
        Produto produto = buscarProdutoPorId(idProduto);
        if (novoValorProdutoDTO.getNovoValor().equals(produto.getValor())) return produto;
        produto.setValor(novoValorProdutoDTO.getNovoValor());
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> buscarTodosPaginado() {
        return CollectionUtils.getListFromIterable(produtoRepository.findAll());
    }

    @Override
    public Produto buscarProdutoPorId(String idProduto) {
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException(StringUtils.buscarMensagemDeValidacao("produto.inexistente", idProduto)));
    }
}
