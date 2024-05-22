package br.com.fiap.adj.techchallenge.getaf.service;

import br.com.fiap.adj.techchallenge.getaf.dto.TicketDTO;
import br.com.fiap.adj.techchallenge.getaf.dto.UpdateTicketDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Ticket;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> getTickets();
    Optional<Ticket> getTicket(Long id);
    void deleteTicket(Long id);
    Ticket createTicket(TicketDTO ticket);

    Optional<Ticket> updateTicket(Long id, UpdateTicketDTO ticketDTODto) throws EntityNotFoundException;
}
