package br.com.digio.compra.backend.repository;

import br.com.digio.compra.backend.domain.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends CrudRepository<Compra, String> {
}
