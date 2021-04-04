package br.com.digio.compra.backend.resources;

import br.com.digio.compra.backend.dto.ClienteDTO;
import br.com.digio.compra.backend.dto.RestResponseDTO;
import br.com.digio.compra.backend.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/digio/cliente")
public class ClienteLeituraResource implements ResponseResource {

    private final ClienteService clienteService;
    private final ModelMapper mapper;

    @Autowired
    public ClienteLeituraResource(ClienteService clienteService, ModelMapper mapper) {
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<RestResponseDTO<Page<ClienteDTO>>> buscarClientes() {
        return retornarSucesso(new PageImpl<>(clienteService.buscarClientesPaginado()
                .stream().map(cliente -> mapper.map(cliente, ClienteDTO.class)).collect(Collectors.toList())));
    }

    @GetMapping("/parametro")
    public ResponseEntity<RestResponseDTO<ClienteDTO>> buscarClientePorParametro(@RequestParam(required = false) String cpf,
                                                                                 @RequestParam(required = false) String email) {
        return retornarSucesso(mapper.map(clienteService.buscarPorParametro(cpf, email), ClienteDTO.class));
    }
}
