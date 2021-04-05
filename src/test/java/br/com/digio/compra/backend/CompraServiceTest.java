package br.com.digio.compra.backend;

import br.com.digio.compra.backend.domain.Compra;
import br.com.digio.compra.backend.dto.NovaCompraDTO;
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

}
