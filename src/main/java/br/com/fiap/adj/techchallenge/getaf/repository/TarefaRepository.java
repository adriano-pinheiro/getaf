package br.com.fiap.adj.techchallenge.getaf.repository;

import br.com.fiap.adj.techchallenge.getaf.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
