package br.com.digio.compra.backend.enumerable;

public enum ECategoria {

    PRIMARIO("Primário", 0.9),
    SECUNDARIO( "Secundário", 0.95);

    private final String nome;
    private final Double desconto;

    ECategoria(String nome, Double desconto) {
        this.nome = nome;
        this.desconto = desconto;
    }

    public String getNome() {
        return nome;
    }
    public Double getDesconto() {
        return desconto;
    }
}
