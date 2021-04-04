package br.com.digio.compra.backend.resources;

import br.com.digio.compra.backend.dto.ClienteDTO;
import br.com.digio.compra.backend.dto.NovoClienteDTO;
import br.com.digio.compra.backend.dto.NovoNomeClienteDTO;
import br.com.digio.compra.backend.dto.RestResponseDTO;
import br.com.digio.compra.backend.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/digio/cliente")
public class ClienteEscritaResource implements ResponseResource {

    private final ClienteService clienteService;
    private final ModelMapper mapper;

    @Autowired
    public ClienteEscritaResource(ClienteService clienteService, ModelMapper mapper) {
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<RestResponseDTO<ClienteDTO>> salvarCliente(@RequestBody NovoClienteDTO novoClienteDTO) {
        return retornarSucesso(mapper.map(clienteService.salvarCliente(novoClienteDTO), ClienteDTO.class));
    }

    @PutMapping("/{idCliente}/atualizar-nome")
    public ResponseEntity<RestResponseDTO<ClienteDTO>> atualizarNomeCliente(@PathVariable String idCliente,
                                                                            @RequestBody NovoNomeClienteDTO novoNomeClienteDTO) {
        return retornarSucesso(mapper.map(clienteService.alterarNomeCliente(novoNomeClienteDTO, idCliente), ClienteDTO.class));
    }
}
