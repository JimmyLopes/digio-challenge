package br.com.digio.compra.backend.domain;

import br.com.digio.compra.backend.enumerable.ECategoria;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Produto")
@TypeAlias("Produto")
public class Produto {

    @Indexed
    private String id;
    private String nome;
    private Double valor;
    private ECategoria categoria;
    private boolean isMaioridade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ECategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ECategoria categoria) {
        this.categoria = categoria;
    }

    public boolean getIsMaioridade() {
        return isMaioridade;
    }

    public void setIsMaioridade(boolean isMaioridade) {
        this.isMaioridade = isMaioridade;
    }
}
