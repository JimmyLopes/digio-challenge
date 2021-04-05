package br.com.digio.compra.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovoValorProdutoDTO {

    @NotNull(message = "produto.novo.valor.vazio")
    @Positive(message = "produto.novo.valor.invalido")
    private Double novoValor;

    public Double getNovoValor() {
        return novoValor;
    }

    public void setNovoValor(Double novoValor) {
        this.novoValor = novoValor;
    }
}
