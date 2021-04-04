package br.com.digio.compra.backend;

import br.com.digio.compra.backend.domain.Cliente;
import br.com.digio.compra.backend.domain.Compra;
import br.com.digio.compra.backend.domain.Produto;
import br.com.digio.compra.backend.enumerable.ECategoria;
import br.com.digio.compra.backend.repository.ClienteRepository;
import br.com.digio.compra.backend.repository.CompraRepository;
import br.com.digio.compra.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	public ClienteRepository clienteRepository;

	@Autowired
	public ProdutoRepository produtoRepository;

	@Autowired
	public CompraRepository compraRepository;

	@Override
	public void run(String... args) throws Exception {
		//Deleta a Base de teste
		clienteRepository.deleteAll();
		produtoRepository.deleteAll();
		compraRepository.deleteAll();

		//Cria os obj de teste
		Cliente clienteTest1 = new Cliente();
		clienteTest1.setId("1");
		clienteTest1.setNome("Jimmy Botezine");
		clienteTest1.setCpf("35121185006");
		clienteTest1.setDataNascimento(LocalDate.of(1995,11,30));
		clienteTest1.setEmail("jlopes@gmail.com");

		Cliente clienteTest2 = new Cliente();
		clienteTest2.setNome("Rodrigo Perobelli");
		clienteTest2.setCpf("54620300020");
		clienteTest2.setDataNascimento(LocalDate.now());
		clienteTest2.setEmail("rperobelli@gmail.com");

		Produto produtoTest1 = new Produto();
		produtoTest1.setNome("Caneta");
		produtoTest1.setValor(1.00);
		produtoTest1.setIsMaioridade(false);
		produtoTest1.setCategoria(ECategoria.SECUNDARIO);

		Produto produtoTest2 = new Produto();
		produtoTest2.setNome("Cerveja");
		produtoTest2.setValor(3.49);
		produtoTest2.setIsMaioridade(true);
		produtoTest2.setCategoria(ECategoria.PRIMARIO);

		Compra compraTest1 = new Compra();
		compraTest1.setDataCompra(LocalDate.now());
		compraTest1.setCliente(clienteTest1);
		compraTest1.setProdutos(Arrays.asList(produtoTest1, produtoTest2, produtoTest2));

		//salva os obj de teste
		compraRepository.save(compraTest1);
		clienteRepository.saveAll(Arrays.asList(clienteTest1, clienteTest2));
		produtoRepository.saveAll(Arrays.asList(produtoTest1, produtoTest2));
	}
}
