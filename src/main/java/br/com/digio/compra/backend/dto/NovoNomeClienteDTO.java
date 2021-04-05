package br.com.digio.compra.backend.dto;

import javax.validation.constraints.NotBlank;

public class NovoNomeClienteDTO {

    @NotBlank(message = "cliente.nome.novo")
    private String novoNomeCliente;

    public String getNovoNomeCliente() {
        return novoNomeCliente;
    }

    public void setNovoNomeCliente(String novoNomeCliente) {
        this.novoNomeCliente = novoNomeCliente;
    }
}
