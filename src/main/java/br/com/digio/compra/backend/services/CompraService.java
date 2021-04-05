package br.com.digio.compra.backend.services;

import br.com.digio.compra.backend.domain.Compra;
import br.com.digio.compra.backend.dto.NovaCompraDTO;

import javax.validation.Valid;
import java.util.List;

public interface CompraService {

    /**
     * Método responsável por salvar uma nova compra
     *
     * @param novaCompraDTO lista contendo os id e quantidade dos produtos adquiridos
     * @param idCliente o id do cliente que está realizando a compra
     * @return a compra realizada
     */
    Compra salvarCompra(@Valid NovaCompraDTO novaCompraDTO, String idCliente);

    /**
     * Método responsável por buscar as compras segundo um parâmetro
     *
     * @param cpf parâmetro de cpf
     * @param dataCompra parâmetro de data compra
     * @return a compra procurada
     */
    List<Compra> buscarCompras(String cpf, String dataCompra);
}
