package br.com.digio.compra.backend.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class NovoClienteDTO {

    @NotBlank(message = "cliente.nome.vazio")
    private String nomeCliente;
    @CPF(message = "cliente.cpf.invalido")
    private String cpf;
    @Email(message = "cliente.email.invalido")
    private String emailCliente;
    @NotNull(message = "cliente.data.nascimento.vazio")
    @PastOrPresent(message = "cliente.data.nascimento.futuro")
    private LocalDate dataNascimento;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
