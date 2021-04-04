package br.com.digio.compra.backend.dto;

import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.enumerable.ECategoria;
import br.com.digio.compra.backend.utils.StringUtils;

public class ProdutoDTO {

    private String nomeProduto;
    private String categoria;
    private Double valor;
    private String isMaioridade;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.categoria = produto.getCategoria().getNome();
        this.valor = produto.getValor();
        this.isMaioridade = StringUtils.converterBooleanParaString(String.valueOf(produto.getIsMaioridade()));
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoria() {
        return ECategoria.valueOf(categoria).getNome();
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getIsMaioridade() {
        return StringUtils.converterBooleanParaString(isMaioridade);
    }

    public void setIsMaioridade(String isMaioridade) {
        this.isMaioridade = isMaioridade;
    }
}
