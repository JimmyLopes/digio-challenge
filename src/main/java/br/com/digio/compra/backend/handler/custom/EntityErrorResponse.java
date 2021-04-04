package br.com.digio.compra.backend.handler.custom;

import java.util.List;

/**
 * Objeto construido a partir da lista de erros
 */
public class EntityErrorResponse extends AbstractErrorResponse {

    private List<StandardError> erros;

    public EntityErrorResponse() {
    }

    public EntityErrorResponse(String mensagem, int codigo, String status,
                               List<StandardError> erros) {
        super(mensagem, codigo, status);
        this.erros = erros;
    }

    public List<StandardError> getErros() {
        return erros;
    }

    public void setErros(List<StandardError> erros) {
        this.erros = erros;
    }
}
