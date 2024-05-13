package br.com.fiap.adj.techchallenge.getaf.service.impl;

import br.com.fiap.adj.techchallenge.getaf.dto.TicketDTO;
import br.com.fiap.adj.techchallenge.getaf.dto.UpdateTicketDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Ticket;
import br.com.fiap.adj.techchallenge.getaf.repository.TicketRepository;
import br.com.fiap.adj.techchallenge.getaf.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repository;


    public Ticket createTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO,ticket);
        return repository.save(ticket);
    }

    @Override
    public List<Ticket> getTickets() {
        return repository.findAll();
    }

    @Override
    public Optional<Ticket> getTicket(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Ticket> updateTicket(Long id, UpdateTicketDTO ticketDTODto) {
        Optional<Ticket> ticketOptional = repository.findById(id);
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setNome(ticketDTODto.nome());
            ticket.setDescricao(ticketDTODto.descricao());
            ticket.setPrioridade(ticketDTODto.prioridade());
            ticket.setStatus(ticketDTODto.status());
            ticket.setHorasOrcadas(ticketDTODto.horasOrcadas());
            //id e id do cliente não podem ser atualizados de acordo com regra atual
            repository.save(ticket);
        }
        return ticketOptional;
    }

    @Override
    public void deleteTicket(Long id) throws EntityNotFoundException{
        Optional<Ticket> ticketOptional = repository.findById(id);
        if(ticketOptional.isPresent()){
            repository.delete(ticketOptional.get());
        }else{
            throw new EntityNotFoundException();
        }

    }
}
