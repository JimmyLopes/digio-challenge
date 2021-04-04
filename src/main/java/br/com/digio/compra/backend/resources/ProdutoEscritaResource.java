package br.com.digio.compra.backend.resources;

import br.com.digio.compra.backend.dto.NovoProdutoDTO;
import br.com.digio.compra.backend.dto.NovoValorProdutoDTO;
import br.com.digio.compra.backend.dto.ProdutoDTO;
import br.com.digio.compra.backend.dto.RestResponseDTO;
import br.com.digio.compra.backend.services.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/digio/produto")
public class ProdutoEscritaResource implements ResponseResource {

    private final ProdutoService produtoService;
    private final ModelMapper mapper;

    @Autowired
    public ProdutoEscritaResource(ProdutoService produtoService, ModelMapper mapper) {
        this.produtoService = produtoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<RestResponseDTO<ProdutoDTO>> salvarProduto(@RequestBody NovoProdutoDTO novoProdutoDTO) {
        return retornarSucesso(mapper.map(produtoService.salvarProduto(novoProdutoDTO), ProdutoDTO.class));
    }

    @PutMapping("/{idProduto}/atualizar-valor")
    public ResponseEntity<RestResponseDTO<ProdutoDTO>> atualizarValorProduto(@PathVariable String idProduto,
                                                                             @RequestBody NovoValorProdutoDTO novoValorProdutoDTO) {
        return retornarSucesso(mapper.map(produtoService.alterarValorProduto(novoValorProdutoDTO, idProduto), ProdutoDTO.class));
    }

    @DeleteMapping("/{idProduto}/excluir")
    public ResponseEntity<RestResponseDTO<List<Object>>> excluirProduto(@PathVariable String idProduto) {
        produtoService.excluirProduto(idProduto);
        return retornarSucesso(Collections.emptyList());
    }
}
