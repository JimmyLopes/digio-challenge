package br.com.digio.compra.backend.dto;

import br.com.digio.compra.backend.enumerable.EResponseStatus;

public class RestResponseDTO<T> {

	private EResponseStatus status;
	private T resultado;

	public RestResponseDTO() {
		super();
	}
	
	public RestResponseDTO(final EResponseStatus response, final T dados) {
		super();
		this.status = response;
		this.resultado = dados;
	}

	public EResponseStatus getStatus() {
		return status;
	}

	public void setStatus(EResponseStatus status) {
		this.status = status;
	}

	public T getResultado() {
		return resultado;
	}

	public void setResultado(T resultado) {
		this.resultado = resultado;
	}

	
}
