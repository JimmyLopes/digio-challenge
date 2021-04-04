package br.com.digio.compra.backend.dto;

import br.com.digio.compra.backend.domain.Compra;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CompraDTO {

    private ClienteDTO cliente;
    private List<ProdutoDTO> produtos;
    private Double valorCompra;
    private LocalDate dataCompra;

    public CompraDTO() {
    }

    public CompraDTO(Compra compra) {
        this.cliente = new ClienteDTO(compra.getCliente());
        this.produtos = compra.getProdutos().stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}
