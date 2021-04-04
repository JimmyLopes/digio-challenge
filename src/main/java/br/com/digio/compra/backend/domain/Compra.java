package br.com.digio.compra.backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RedisHash("Compra")
@TypeAlias("Compra")
public class Compra {

    @Id
    private String id;
    private Cliente cliente;
    private List<Produto> produtos = new ArrayList<>();
    @Indexed
    private LocalDate dataCompra;
    private Double valorCompra;
    private Double valorCompraComDesconto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getValorCompraComDesconto() {
        return valorCompraComDesconto;
    }

    public void setValorCompraComDesconto(Double valorCompraComDesconto) {
        this.valorCompraComDesconto = valorCompraComDesconto;
    }
}
