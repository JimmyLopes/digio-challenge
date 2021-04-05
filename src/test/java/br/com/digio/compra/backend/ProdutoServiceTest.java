package br.com.digio.compra.backend;

import br.com.digio.compra.backend.config.ModelMapperConfig;
import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.dto.NovoProdutoDTO;
import br.com.digio.compra.backend.dto.NovoValorProdutoDTO;
import br.com.digio.compra.backend.repository.ProdutoRepository;
import br.com.digio.compra.backend.services.ProdutoService;
import br.com.digio.compra.backend.services.impl.ProdutoServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utils.TestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProdutoServiceTest {

    protected static final String MOCK_FOLDER_PRODUTO = "/mocks/produto";
    protected static final String PRODUTO_JSON = "produto.json";
    protected static final String NOVO_PRODUTO_JSON = "novoProdutoDto.json";
    protected static final String NOVO_VALOR_PRODUTO_JSON = "novoValorProdutoDto.json";
    protected final ModelMapper mapper = new ModelMapperConfig().modelMapper();

    @MockBean
    public ProdutoRepository produtoRepository;

    public static Produto getMockProduto() {
        return TestUtils.getMock(MOCK_FOLDER_PRODUTO, PRODUTO_JSON, Produto.class);
    }

    public static NovoProdutoDTO getMockNovoProdutoDto() {
        return TestUtils.getMock(MOCK_FOLDER_PRODUTO, NOVO_PRODUTO_JSON, NovoProdutoDTO.class);
    }

    public static NovoValorProdutoDTO getMockNovoValorProdutoDto() {
        return TestUtils.getMock(MOCK_FOLDER_PRODUTO, NOVO_VALOR_PRODUTO_JSON, NovoValorProdutoDTO.class);
    }

    public ProdutoService produtoService() {
        return new ProdutoServiceImpl(produtoRepository, mapper);
    }

    @Test
    @DisplayName("testarSalvarProduto 01 - Testa se um produto vai ser salvo de forma correta")
    public void testarSalvarProduto() {
        Produto mockProduto = getMockProduto();
        when(produtoRepository.save(Mockito.any())).thenReturn(mockProduto);
        Produto produto = produtoService().salvarProduto(getMockNovoProdutoDto());

        Assert.assertNotNull(produto);
        Assert.assertEquals(mockProduto.getNome(), produto.getNome());
        Assert.assertEquals(mockProduto.getCategoria(), produto.getCategoria());
        Assert.assertEquals(mockProduto.getIsMaioridade(), produto.getIsMaioridade());
        Assert.assertEquals(mockProduto.getValor(), produto.getValor());
    }

    @Test
    @DisplayName("testarExcluirProduto 02 - Testa se um produto vai ser excluido de forma correta")
    public void testarExcluirProduto() {
        Produto mockProduto = getMockProduto();
        when(produtoRepository.findById(Mockito.any())).thenReturn(Optional.of(mockProduto));
        produtoService().excluirProduto(mockProduto.getId());
        verify(produtoRepository, times(1)).delete(mockProduto);
    }

    @Test
    @DisplayName("testarAlterarValorProduto 03 - Testa se um produto vai ter seu valor alterado de forma correta")
    public void testarAlterarValorProduto() {
        Produto mockProduto = getMockProduto();
        when(produtoRepository.findById(Mockito.any())).thenReturn(Optional.of(mockProduto));
        when(produtoRepository.save(Mockito.any())).thenReturn(mockProduto);
        Produto produto = produtoService().alterarValorProduto(getMockNovoValorProdutoDto(), mockProduto.getId());

        Assert.assertNotNull(produto);
        Assert.assertEquals(mockProduto.getNome(), produto.getNome());
        Assert.assertEquals(mockProduto.getCategoria(), produto.getCategoria());
        Assert.assertEquals(mockProduto.getIsMaioridade(), produto.getIsMaioridade());
        Assert.assertEquals(mockProduto.getValor(), produto.getValor());
    }

    @Test
    @DisplayName("testarBuscarTodosProdutos 04 - Testa se o serviço vai realizar a busca para todos os produtos")
    public void testarBuscarTodosProdutos() {
        when(produtoRepository.findAll()).thenReturn(Collections.singletonList(getMockProduto()));
        List<Produto> produtos = produtoService().buscarTodosProdutos();

        Assert.assertFalse(produtos.isEmpty());
        Assert.assertEquals(1, produtos.size());
    }

}
