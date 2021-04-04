package br.com.digio.compra.backend.resources;

import br.com.digio.compra.backend.dto.RestResponseDTO;
import br.com.digio.compra.backend.enumerable.EResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseResource {

    default <T> ResponseEntity<RestResponseDTO<T>> retornarSucesso(final T response) {

        RestResponseDTO<T> restResponse = new RestResponseDTO<>();

        if (response == null || (response instanceof List<?> && ((List<?>) response).isEmpty())) {
            restResponse.setStatus(EResponseStatus.SEM_RESULTADOS);
        } else {
            restResponse.setStatus(EResponseStatus.OK);
        }

        restResponse.setResultado(response);

        return ResponseEntity.status(HttpStatus.OK).body(restResponse);
    }
}