package br.com.digio.compra.backend.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/digio/health")
public class HealthResource {

    /**
     * Endpoint para verificar se a aplicação está ativa
     *
     * @return sucesso caso a requisão chegue na aplicação
     */
    @GetMapping
    public ResponseEntity<Object> checkStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
