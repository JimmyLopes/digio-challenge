package br.com.digio.compra.backend.services;

import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.dto.NovoProdutoDTO;
import br.com.digio.compra.backend.dto.NovoValorProdutoDTO;

import javax.validation.Valid;
import java.util.List;

public interface ProdutoService {
    /**
     * Método responsável por salvar um novo produto
     *
     * @param novoProdutoDTO o novo produto a ser salvo
     * @return o produto salvo
     */
    Produto salvarProduto(@Valid NovoProdutoDTO novoProdutoDTO);

    /**
     * Método responsável por excluir um produto existente
     *
     * @param idProduto o id do produto a ser excluído
     */
    void excluirProduto(String idProduto);

    /**
     * Método responsável por alterar o valor de um produto já existente
     *
     * @param novoValorProdutoDTO o novo valor do produto
     * @param idProduto o id do produto cujo o valor está sendo alterado
     * @return o produto com o valor alterado
     */
    Produto alterarValorProduto(NovoValorProdutoDTO novoValorProdutoDTO, String idProduto);

    /**
     * Método responsável por buscar todos os produtos cadastrados
     *
     * @return
     */
    List<Produto> buscarTodosProdutos();

    /**
     * Método responsável por buscar um produto pelo seu id
     *
     * @param id
     * @return
     */
    Produto buscarProdutoPorId(String id);
}
