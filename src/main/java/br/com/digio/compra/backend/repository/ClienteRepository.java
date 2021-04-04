package br.com.digio.compra.backend.repository;

import br.com.digio.compra.backend.domain.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);
}
