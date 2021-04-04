package br.com.digio.compra.backend.handler;

import br.com.digio.compra.backend.handler.custom.EntityErrorResponse;
import br.com.digio.compra.backend.handler.custom.StandardError;
import br.com.digio.compra.backend.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ConstraintValidationExceptionHandler {

    /**
     * Captura a exceção lançada pelas anotações nos DTO e melhora o retorno para o usuário
     *
     * @param exception a exceção lançada
     * @return o response contendo uma mensagem mais amigavél e os erros na request
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<EntityErrorResponse> handleConstraintViolation(ConstraintViolationException exception) {

        List<StandardError> errors = getErrors(exception);
        EntityErrorResponse errorResponse = getErrorResponse(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Constroi o objeto de Entity Error Response a partir da lista de erros
     *
     * @param errors a lista de erros
     * @return o objeto de EntityErrorResponse
     */
    private EntityErrorResponse getErrorResponse(List<StandardError> errors) {
        return new EntityErrorResponse("Erro ao salvar os dados.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                errors);
    }

    /**
     * Extrai os erros vindo da requisição e mapeira para uma lista de StandartError
     *
     * @param ex a exceção lançada
     * @return a lista de StandartError
     */
    private List<StandardError> getErrors(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(
                error -> new StandardError(StringUtils.buscarMensagemDeValidacao(error.getMessage())))
                .collect(Collectors.toList());
    }
}
