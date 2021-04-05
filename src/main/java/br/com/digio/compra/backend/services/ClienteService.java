package br.com.digio.compra.backend.services;

import br.com.digio.compra.backend.domain.Cliente;
import br.com.digio.compra.backend.dto.NovoClienteDTO;
import br.com.digio.compra.backend.dto.NovoNomeClienteDTO;

import javax.validation.Valid;
import java.util.List;

public interface ClienteService {

    /**
     * Método responsável por salvar um cliente no banco
     *
     * @param novoClienteDTO o objeto de novo cliente validado
     * @return o cliente salvo
     */
    Cliente salvarCliente(@Valid NovoClienteDTO novoClienteDTO);

    /**
     * Método responsável por alterar o nome de um cliente já cadastrado
     *
     * @param novoNomeClienteDTO o nome novo do cliente
     * @param idCliente o Id do cliente cujo nome será alterado
     * @return o cliente com o nome alterado
     */
    Cliente alterarNomeCliente(@Valid NovoNomeClienteDTO novoNomeClienteDTO, String idCliente);

    /**
     * Método responsável por busca todos os clientes cadastrados
     *
     * @return a lista com todos os clientes
     */
    List<Cliente> buscarClientes();

    /**
     * Método responsável por realizar a busca de um cliente por cpf ou por email
     *
     * @param cpf parâmetro de cpf
     * @param email parâmetro de email
     * @return o Cliente procurado
     */
    Cliente buscarClientePorParametro(String cpf, String email);

    /**
     * Método responsável por realizar a busca de um cliente por id
     *
     * @param idCliente o id do cliente procurado
     * @return o Cliente procurado
     */
    Cliente buscarClientePorId(String idCliente);
}
