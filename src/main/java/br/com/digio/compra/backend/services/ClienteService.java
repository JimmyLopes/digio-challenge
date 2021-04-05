package br.com.digio.compra.backend.services;

import br.com.digio.compra.backend.domain.Cliente;
import br.com.digio.compra.backend.dto.NovoClienteDTO;
import br.com.digio.compra.backend.dto.NovoNomeClienteDTO;

import javax.validation.Valid;
import java.util.List;

public interface ClienteService {

    Cliente salvarCliente(@Valid NovoClienteDTO novoClienteDTO);

    Cliente alterarNomeCliente(@Valid NovoNomeClienteDTO novoNomeClienteDTO, String idCliente);

    List<Cliente> buscarClientes();

    Cliente buscarClientePorParametro(String cpf, String email);

    Cliente buscarClientePorId(String idCliente);
}
