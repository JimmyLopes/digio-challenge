package br.com.digio.compra.backend.resources;

import br.com.digio.compra.backend.dto.CompraDTO;
import br.com.digio.compra.backend.dto.NovaCompraDTO;
import br.com.digio.compra.backend.dto.RestResponseDTO;
import br.com.digio.compra.backend.services.CompraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/digio/compra")
public class CompraEscritaResource implements ResponseResource {

    private final CompraService compraService;
    private final ModelMapper mapper;

    @Autowired
    public CompraEscritaResource(CompraService compraService, ModelMapper mapper) {
        this.compraService = compraService;
        this.mapper = mapper;
    }

    @PostMapping("/{idCliente}/nova-compra")
    public ResponseEntity<RestResponseDTO<CompraDTO>> realizarCompra(@RequestBody NovaCompraDTO novaCompraDTO, @PathVariable String idCliente) {
        return retornarSucesso(mapper.map(compraService.salvarCompra(novaCompraDTO, idCliente), CompraDTO.class));
    }
}
