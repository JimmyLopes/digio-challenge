package br.com.digio.compra.backend.enumerable;

public enum EResponseStatus {

	OK("OK"),
	SEM_RESULTADOS("Sem Resultados");

	private final String nome;

	EResponseStatus(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
