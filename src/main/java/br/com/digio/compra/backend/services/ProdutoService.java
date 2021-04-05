package br.com.digio.compra.backend.services;

import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.dto.NovoProdutoDTO;
import br.com.digio.compra.backend.dto.NovoValorProdutoDTO;

import javax.validation.Valid;
import java.util.List;

public interface ProdutoService {
    Produto salvarProduto(@Valid NovoProdutoDTO novoProdutoDTO);

    void excluirProduto(String idProduto);

    Produto alterarValorProduto(NovoValorProdutoDTO novoValorProdutoDTO, String idProduto);

    List<Produto> buscarTodosProdutos();

    Produto buscarProdutoPorId(String id);
}
