package br.com.digio.compra.backend.dto;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class NovaCompraDTO {

    @Size(min = 1, message = "compra.vazio")
    private List<ProdutoCompraDTO> produtos = new ArrayList<>();

    public List<ProdutoCompraDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoCompraDTO> produtos) {
        this.produtos = produtos;
    }
}
