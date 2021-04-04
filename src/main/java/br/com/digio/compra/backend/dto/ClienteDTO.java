package br.com.digio.compra.backend.dto;

import br.com.digio.compra.backend.domain.Cliente;

public class ClienteDTO {

    private String nomeCliente;
    private String emailCliente;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.nomeCliente = cliente.getNome();
        this.emailCliente = cliente.getEmail();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
}
