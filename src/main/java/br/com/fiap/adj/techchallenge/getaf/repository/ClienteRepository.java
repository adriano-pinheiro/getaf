package br.com.fiap.adj.techchallenge.getaf.repository;

import br.com.fiap.adj.techchallenge.getaf.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
