package br.com.fiap.adj.techchallenge.getaf.repository;

import br.com.fiap.adj.techchallenge.getaf.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
