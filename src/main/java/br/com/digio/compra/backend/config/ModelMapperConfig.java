package br.com.digio.compra.backend.config;

import br.com.digio.compra.backend.domain.Compra;
import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.dto.CompraDTO;
import br.com.digio.compra.backend.dto.NovoProdutoDTO;
import br.com.digio.compra.backend.dto.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        //Mapping Produto to ProdutoDTO
        PropertyMap<Produto, ProdutoDTO> produtoDTOPropertyMap = new PropertyMap<Produto, ProdutoDTO>() {
            protected void configure() {
                map().setIsMaioridade(String.valueOf(source.getIsMaioridade()));
            }
        };

        //Mapping NovoProdutoDTO to Produto
        PropertyMap<NovoProdutoDTO, Produto> produtoPropertyMap = new PropertyMap<NovoProdutoDTO, Produto>() {
            protected void configure() {
                map().setCategoria(source.getCategoria());
                map().setIsMaioridade(source.getIsMarioridade());
            }
        };

        //Mapping Compra to CompraDTO
        PropertyMap<Compra, CompraDTO> CompraDTOPropertyMap = new PropertyMap<Compra, CompraDTO>() {
            protected void configure() {
                map().setValorCompra(source.getValorCompraComDesconto());
            }
        };

        mapper.addMappings(produtoDTOPropertyMap);
        mapper.addMappings(produtoPropertyMap);
        mapper.addMappings(CompraDTOPropertyMap);

        return mapper;
    }
}
