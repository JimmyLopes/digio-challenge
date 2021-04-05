package br.com.digio.compra.backend.services.impl;

import br.com.digio.compra.backend.domain.Cliente;
import br.com.digio.compra.backend.dto.NovoClienteDTO;
import br.com.digio.compra.backend.dto.NovoNomeClienteDTO;
import br.com.digio.compra.backend.exceptions.BusinessException;
import br.com.digio.compra.backend.exceptions.ResourceNotFoundException;
import br.com.digio.compra.backend.repository.ClienteRepository;
import br.com.digio.compra.backend.services.ClienteService;
import br.com.digio.compra.backend.utils.CollectionUtils;
import br.com.digio.compra.backend.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper mapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Cliente salvarCliente(@Valid NovoClienteDTO novoClienteDTO) {
        Cliente cliente = mapper.map(novoClienteDTO, Cliente.class);
        if (isEmailDuplicado(cliente.getEmail())) {
            throw new BusinessException(StringUtils.buscarMensagemDeValidacao("email.existente", novoClienteDTO.getEmailCliente()));
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente alterarNomeCliente(@Valid NovoNomeClienteDTO novoNomeClienteDTO, String idCliente) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (novoNomeClienteDTO.getNovoNomeCliente().equals(cliente.getNome()))
            return cliente;
        cliente.setNome(novoNomeClienteDTO.getNovoNomeCliente());
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> buscarClientes() {
        return CollectionUtils.getListFromIterable(clienteRepository.findAll());
    }

    @Override
    public Cliente buscarClientePorParametro(String cpf, String email) {
        validaParametros(cpf, email);
        Cliente cliente = null;
        if (cpf != null) cliente = this.buscarClientePorCpf(cpf);
        if (email != null) cliente = this.buscarClientePorEmail(email);
        return cliente;
    }

    @Override
    public Cliente buscarClientePorId(String idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException(StringUtils.buscarMensagemDeValidacao("cliente.nao.encontrado", idCliente)));
    }

    private Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException(StringUtils.buscarMensagemDeValidacao("cliente.cpf.inexistente", cpf)));
    }

    private Cliente buscarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(StringUtils.buscarMensagemDeValidacao("cliente.email.inexistente", email)));
    }

    private boolean isEmailDuplicado(String email) {
        return CollectionUtils.getListFromIterable(clienteRepository.findAll()).stream().anyMatch(cliente -> cliente.getEmail().equals(email));
    }

    private void validaParametros(String cpf, String email) {
        if (cpf != null && email != null)
            throw new BusinessException(StringUtils.buscarMensagemDeValidacao("cpf.email.preenchido"));
        if (cpf == null && email == null)
            throw new BusinessException(StringUtils.buscarMensagemDeValidacao("cpf.email.nao.preenchido"));
    }
}
