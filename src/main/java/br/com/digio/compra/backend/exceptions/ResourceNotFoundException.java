package br.com.digio.compra.backend.exceptions;

/**
 * Exceção personalizada para recurso não encontrado
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable causa) {
        super(message, causa);
    }
}
