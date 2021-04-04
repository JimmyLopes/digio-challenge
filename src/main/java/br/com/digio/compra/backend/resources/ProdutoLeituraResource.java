package br.com.digio.compra.backend.resources;

import br.com.digio.compra.backend.dto.ProdutoDTO;
import br.com.digio.compra.backend.dto.RestResponseDTO;
import br.com.digio.compra.backend.services.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/digio/produto")
public class ProdutoLeituraResource implements ResponseResource {

    private final ProdutoService produtoService;
    private final ModelMapper mapper;

    @Autowired
    public ProdutoLeituraResource(ProdutoService produtoService, ModelMapper mapper) {
        this.produtoService = produtoService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<RestResponseDTO<Page<ProdutoDTO>>> buscarProdutos() {
        return retornarSucesso(new PageImpl<>(produtoService.buscarTodosPaginado()
                .stream().map(produto -> mapper.map(produto, ProdutoDTO.class)).collect(Collectors.toList())));
    }
}
