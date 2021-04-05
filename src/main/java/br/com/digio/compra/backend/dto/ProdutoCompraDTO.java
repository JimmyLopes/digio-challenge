package br.com.digio.compra.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProdutoCompraDTO {

    @NotBlank(message = "compra.produto.id.vazio")
    private String idProduto;

    @Positive(message = "compra.produto.quantidade")
    private Integer quantidade;

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
