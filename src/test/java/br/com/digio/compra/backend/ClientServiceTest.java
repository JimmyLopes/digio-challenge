package br.com.digio.compra.backend;

import br.com.digio.compra.backend.config.ModelMapperConfig;
import br.com.digio.compra.backend.domain.Cliente;
import br.com.digio.compra.backend.dto.NovoClienteDTO;
import br.com.digio.compra.backend.dto.NovoNomeClienteDTO;
import br.com.digio.compra.backend.exceptions.BusinessException;
import br.com.digio.compra.backend.repository.ClienteRepository;
import br.com.digio.compra.backend.services.ClienteService;
import br.com.digio.compra.backend.services.impl.ClienteServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utils.TestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ClientServiceTest {

    protected static final String MOCK_FOLDER_CLIENTE = "/mocks/cliente";
    protected static final String CLIENTE_JSON = "cliente.json";
    protected static final String NOVO_CLIENTE_JSON = "novoClienteDto.json";
    protected static final String NOVO_NOME_CLIENTE_JSON = "novoNomeClienteDto.json";
    protected final ModelMapper mapper = new ModelMapperConfig().modelMapper();

    @MockBean
    public ClienteRepository clienteRepository;

    public static Cliente getMockCliente() {
        return TestUtils.getMock(MOCK_FOLDER_CLIENTE, CLIENTE_JSON, Cliente.class);
    }

    public static NovoClienteDTO getMockNovoClienteDto() {
        return TestUtils.getMock(MOCK_FOLDER_CLIENTE, NOVO_CLIENTE_JSON, NovoClienteDTO.class);
    }

    public static NovoNomeClienteDTO getMockNovoNomeClienteDto() {
        return TestUtils.getMock(MOCK_FOLDER_CLIENTE, NOVO_NOME_CLIENTE_JSON, NovoNomeClienteDTO.class);
    }

    public ClienteService clienteService() {
        return new ClienteServiceImpl(clienteRepository, mapper);
    }

    @Test
    @DisplayName("testarSalvarCliente 01 - Testa se um cliente vai ser salvo de forma correta")
    public void testarSalvarCliente() {
        Cliente mockCliente = getMockCliente();
        when(clienteRepository.save(Mockito.any())).thenReturn(mockCliente);
        Cliente cliente = clienteService().salvarCliente(getMockNovoClienteDto());

        Assert.assertNotNull(cliente);
        Assert.assertEquals(mockCliente.getNome(), cliente.getNome());
        Assert.assertEquals(mockCliente.getCpf(), cliente.getCpf());
        Assert.assertEquals(mockCliente.getEmail(), cliente.getEmail());
        Assert.assertEquals(mockCliente.getDataNascimento(), cliente.getDataNascimento());
        Assert.assertEquals(mockCliente.getIdade(), cliente.getIdade());
    }

    @Test(expected = BusinessException.class)
    @DisplayName("testarSalvarClienteEmailDuplicado 02 - Testa se o sistema vai lançar exceção caso um email do novo cliente já exista na base")
    public void testarSalvarClienteEmailDuplicado() {
        Cliente mockCliente = getMockCliente();
        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(mockCliente));
        clienteService().salvarCliente(getMockNovoClienteDto());
    }

    @Test
    @DisplayName("testarAlterarNomeCliente 03 - Testa se o nome do cliente vai ser atualizado")
    public void testarAlterarNomeCliente() {
        Cliente mockCliente = getMockCliente();
        NovoNomeClienteDTO mockNovoNomeClienteDto = getMockNovoNomeClienteDto();
        mockNovoNomeClienteDto.setNovoNomeCliente("new_fake_data_name");

        when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mockCliente));
        when(clienteRepository.save(Mockito.any())).thenReturn(mockCliente);
        Cliente cliente = clienteService().alterarNomeCliente(mockNovoNomeClienteDto, Mockito.anyString());

        Assert.assertNotNull(cliente);
        Assert.assertEquals(mockCliente.getNome(), cliente.getNome());
        Assert.assertEquals(mockCliente.getCpf(), cliente.getCpf());
        Assert.assertEquals(mockCliente.getEmail(), cliente.getEmail());
        Assert.assertEquals(mockCliente.getDataNascimento(), cliente.getDataNascimento());
        Assert.assertEquals(mockCliente.getIdade(), cliente.getIdade());
    }

    @Test
    @DisplayName("testarAlterarNomeClienteIgualAoExistente 04 - Testa se o cliente vai ser retornado no caso do nome já existente ser igual ao novo")
    public void testarAlterarNomeClienteIgualAoExistente() {
        Cliente mockCliente = getMockCliente();
        NovoNomeClienteDTO mockNovoNomeClienteDto = getMockNovoNomeClienteDto();

        when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mockCliente));
        when(clienteRepository.save(Mockito.any())).thenReturn(mockCliente);
        Cliente cliente = clienteService().alterarNomeCliente(mockNovoNomeClienteDto, Mockito.anyString());

        Assert.assertNotNull(cliente);
        Assert.assertEquals(mockCliente.getNome(), cliente.getNome());
        Assert.assertEquals(mockCliente.getCpf(), cliente.getCpf());
        Assert.assertEquals(mockCliente.getEmail(), cliente.getEmail());
        Assert.assertEquals(mockCliente.getDataNascimento(), cliente.getDataNascimento());
        Assert.assertEquals(mockCliente.getIdade(), cliente.getIdade());
    }

    @Test
    @DisplayName("testarBuscarTodosClientes 05 - Testa se o serviço vai realizar a busca para todos os cliente")
    public void testarBuscarTodosClientes() {
        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(getMockCliente()));
        List<Cliente> clientes = clienteService().buscarClientes();

        Assert.assertFalse(clientes.isEmpty());
        Assert.assertEquals(1, clientes.size());
    }

    @Test(expected = BusinessException.class)
    @DisplayName("testarBuscarClienteCpfNuloEEmailNulo 06 - Testa se o serviço vai lançar exceção no caso de ambos os parâmetros serem nulos")
    public void testarBuscarClienteCpfNuloEEmailNulo() {
        clienteService().buscarClientePorParametro(null, null);
    }

    @Test(expected = BusinessException.class)
    @DisplayName("testarBuscarClienteCpfEEmailPreenchidos 07 - Testa se o serviço vai lançar exceção no caso de ambos os parâmetros serem preenchidos")
    public void testarBuscarClienteCpfEEmailPreenchidos() {
        clienteService().buscarClientePorParametro("fake_cpf", "fake_email@mail.com");
    }

    @Test
    @DisplayName("testarBuscarClienteCpf 08 - Testa se o serviço vai buscar um cliente por cpf")
    public void testarBuscarClienteCpf() {
        Cliente mockCliente = getMockCliente();
        when(clienteRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.of(mockCliente));
        Cliente cliente = clienteService().buscarClientePorParametro("69178673062", null);

        Assert.assertNotNull(cliente);
        Assert.assertEquals(mockCliente.getNome(), cliente.getNome());
        Assert.assertEquals(mockCliente.getCpf(), cliente.getCpf());
        Assert.assertEquals(mockCliente.getEmail(), cliente.getEmail());
        Assert.assertEquals(mockCliente.getDataNascimento(), cliente.getDataNascimento());
        Assert.assertEquals(mockCliente.getIdade(), cliente.getIdade());
    }

    @Test
    @DisplayName("testarBuscarClienteEmail 09 - Testa se o serviço vai buscar um cliente por email")
    public void testarBuscarClienteEmail() {
        Cliente mockCliente = getMockCliente();
        when(clienteRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(mockCliente));
        Cliente cliente = clienteService().buscarClientePorParametro(null, "fake_data@mail.com");

        Assert.assertNotNull(cliente);
        Assert.assertEquals(mockCliente.getNome(), cliente.getNome());
        Assert.assertEquals(mockCliente.getCpf(), cliente.getCpf());
        Assert.assertEquals(mockCliente.getEmail(), cliente.getEmail());
        Assert.assertEquals(mockCliente.getDataNascimento(), cliente.getDataNascimento());
        Assert.assertEquals(mockCliente.getIdade(), cliente.getIdade());
    }

}
