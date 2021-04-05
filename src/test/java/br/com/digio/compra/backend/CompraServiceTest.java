package br.com.digio.compra.backend;

import br.com.digio.compra.backend.domain.Cliente;
import br.com.digio.compra.backend.domain.Compra;
import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.dto.NovaCompraDTO;
import br.com.digio.compra.backend.exceptions.BusinessException;
import br.com.digio.compra.backend.repository.CompraRepository;
import br.com.digio.compra.backend.services.ClienteService;
import br.com.digio.compra.backend.services.CompraService;
import br.com.digio.compra.backend.services.ProdutoService;
import br.com.digio.compra.backend.services.impl.CompraServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utils.TestUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CompraServiceTest {

    protected static final String MOCK_FOLDER_COMPRA = "/mocks/compra";
    protected static final String COMPRA_JSON = "compra.json";
    protected static final String NOVO_COMPRA_JSON = "novaCompraDto.json";

    @MockBean
    public CompraRepository compraRepository;

    @MockBean
    public ClienteService clienteService;

    @MockBean
    public ProdutoService produtoService;

    public static Compra getMockCompra() {
        return TestUtils.getMock(MOCK_FOLDER_COMPRA, COMPRA_JSON, Compra.class);
    }

    public static NovaCompraDTO getMockNovaCompraDto() {
        return TestUtils.getMock(MOCK_FOLDER_COMPRA, NOVO_COMPRA_JSON, NovaCompraDTO.class);
    }

    public CompraService compraService() {
        return new CompraServiceImpl(compraRepository, clienteService, produtoService);
    }

    @Test
    @DisplayName("testarSalvarCompra 01 - Testa se uma compra vai ser salvo de forma correta")
    public void testarSalvarProduto() {
        Compra mockCompra = getMockCompra();
        when(clienteService.buscarClientePorId(Mockito.any())).thenReturn(ClientServiceTest.getMockCliente());
        when(produtoService.buscarProdutoPorId(Mockito.any())).thenReturn(ProdutoServiceTest.getMockProduto());
        when(compraRepository.save(Mockito.any())).thenReturn(mockCompra);
        Compra compra = compraService().salvarCompra(getMockNovaCompraDto(), ClientServiceTest.getMockCliente().getId());

        Assert.assertNotNull(compra);
        Assert.assertEquals(mockCompra.getCliente(), compra.getCliente());
        Assert.assertEquals(mockCompra.getProdutos().size(), compra.getProdutos().size());
        Assert.assertEquals(mockCompra.getDataCompra(), compra.getDataCompra());
        Assert.assertEquals(mockCompra.getValorCompra(), compra.getValorCompra());
        Assert.assertEquals(mockCompra.getValorCompraComDesconto(), compra.getValorCompraComDesconto());
    }

    @Test(expected = BusinessException.class)
    @DisplayName("testarExcecaoCompraMenoridade 02 - Testa se o sistema vai lançar exceção no caso de uma cliente tentar fazer a compra de um produto destinado a maioridade")
    public void testarExcecaoCompraMenoridade() {
        Cliente mockCliente = ClientServiceTest.getMockCliente();
        mockCliente.setDataNascimento(LocalDate.now());
        Produto mockProduto = ProdutoServiceTest.getMockProduto();
        mockProduto.setIsMaioridade(true);

        when(clienteService.buscarClientePorId(Mockito.any())).thenReturn(mockCliente);
        when(produtoService.buscarProdutoPorId(Mockito.any())).thenReturn(mockProduto);
        compraService().salvarCompra(getMockNovaCompraDto(), mockCliente.getId());
    }

    @Test(expected = BusinessException.class)
    @DisplayName("testarBuscarPorParametrosComParametrosNulos 03 - Testa se o sistema vai lançar exceção no caso de uma busca com parametros nulos")
    public void testarBuscarPorParametrosComParametrosNulos() {
        compraService().buscarCompras(null, null);
    }

    @Test(expected = BusinessException.class)
    @DisplayName("testarBuscarPorParametrosComParametrosPreenchidos 04 - Testa se o sistema vai lançar exceção no caso de uma busca com ambos parametros preenchidos")
    public void testarBuscarPorParametrosComParametrosPreenchidos() {
        compraService().buscarCompras("fake_cpf", LocalDate.now().toString());
    }

    @Test(expected = BusinessException.class)
    @DisplayName("testarExcecaoConversaoData 05 - Testa se o sistema vai lançar exceção no caso de não conseguir converter uma data")
    public void testarExcecaoConversaoData() {
        compraService().buscarCompras(null, "fake_data");
    }

    @Test
    @DisplayName("testarBuscaPorCpf 06 - Testa se o sistema vai realizar uma busca por cpf")
    public void testarBuscaPorCpf() {
        Compra mockCompra = getMockCompra();
        when(compraRepository.findAll()).thenReturn(Collections.singletonList(mockCompra));
        List<Compra> compras = compraService().buscarCompras("fake_data", null);
        if (!compras.isEmpty()) {
            Compra compra = compras.get(0);

            Assert.assertNotNull(compra);
            Assert.assertEquals(mockCompra.getCliente(), compra.getCliente());
            Assert.assertEquals(mockCompra.getProdutos().size(), compra.getProdutos().size());
            Assert.assertEquals(mockCompra.getDataCompra(), compra.getDataCompra());
            Assert.assertEquals(mockCompra.getValorCompra(), compra.getValorCompra());
            Assert.assertEquals(mockCompra.getValorCompraComDesconto(), compra.getValorCompraComDesconto());
        }
    }

    @Test
    @DisplayName("testarBuscaPorData 07 - Testa se o sistema vai realizar uma busca por data")
    public void testarBuscaPorData() {
        Compra mockCompra = getMockCompra();
        when(compraRepository.findAll()).thenReturn(Collections.singletonList(mockCompra));
        List<Compra> compras = compraService().buscarCompras(null, mockCompra.getDataCompra().toString());
        if (!compras.isEmpty()) {
            Compra compra = compras.get(0);

            Assert.assertNotNull(compra);
            Assert.assertEquals(mockCompra.getCliente(), compra.getCliente());
            Assert.assertEquals(mockCompra.getProdutos().size(), compra.getProdutos().size());
            Assert.assertEquals(mockCompra.getDataCompra(), compra.getDataCompra());
            Assert.assertEquals(mockCompra.getValorCompra(), compra.getValorCompra());
            Assert.assertEquals(mockCompra.getValorCompraComDesconto(), compra.getValorCompraComDesconto());
        }
    }
}
