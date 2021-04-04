package br.com.digio.compra.backend.services;

import br.com.digio.compra.backend.domain.Compra;
import br.com.digio.compra.backend.dto.NovaCompraDTO;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

public interface CompraService {

    Compra salvarCompra(@Valid NovaCompraDTO novaCompraDTO, String idCliente);

    List<Compra> buscarCompras(String cpf, LocalDate dataCompra);
}
