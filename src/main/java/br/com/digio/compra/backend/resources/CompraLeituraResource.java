package br.com.digio.compra.backend.resources;

import br.com.digio.compra.backend.dto.CompraDTO;
import br.com.digio.compra.backend.dto.RestResponseDTO;
import br.com.digio.compra.backend.services.CompraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/digio/compra")
public class CompraLeituraResource implements ResponseResource {

    private final CompraService compraService;
    private final ModelMapper mapper;

    @Autowired
    public CompraLeituraResource(CompraService compraService, ModelMapper mapper) {
        this.compraService = compraService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<RestResponseDTO<List<CompraDTO>>> buscarCompras(@RequestParam(required = false) String cpf,
                                                                          @RequestParam(required = false) String dataCompra) {
        return retornarSucesso(compraService.buscarCompras(cpf, dataCompra)
                .stream().map(compra -> mapper.map(compra, CompraDTO.class)).collect(Collectors.toList()));
    }
}
