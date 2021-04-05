package br.com.digio.compra.backend.dto;

import br.com.digio.compra.backend.enumerable.ECategoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovoProdutoDTO {

    @NotBlank(message = "produto.nome.vazio")
    private String nomeProduto;
    @NotNull(message = "produto.categorio.vazio")
    private ECategoria categoria;
    @Positive(message = "produto.valor.vazio")
    private Double valorProduto;
    @NotNull(message = "produto.maioridade.nulo")
    private boolean isMarioridade;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public ECategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ECategoria categoria) {
        this.categoria = categoria;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public boolean getIsMarioridade() {
        return isMarioridade;
    }

    public void setIsMaioridade(boolean isMaioridade) {
        this.isMarioridade = isMaioridade;
    }
}
