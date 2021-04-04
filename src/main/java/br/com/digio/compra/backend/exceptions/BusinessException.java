package br.com.digio.compra.backend.exceptions;

/**
 * Exceção personalizada para erros que acontecem na regra de negócio
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
