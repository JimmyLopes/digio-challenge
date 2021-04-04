package br.com.digio.compra.backend.repository;

import br.com.digio.compra.backend.domain.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, String> {
}
