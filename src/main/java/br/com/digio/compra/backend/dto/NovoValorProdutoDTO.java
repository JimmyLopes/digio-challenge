package br.com.digio.compra.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovoValorProdutoDTO {
    @NotNull
    @Positive
    private Double novoValor;

    public Double getNovoValor() {
        return novoValor;
    }

    public void setNovoValor(Double novoValor) {
        this.novoValor = novoValor;
    }
}
