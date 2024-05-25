package br.com.fiap.adj.techchallenge.getaf.controller;

import br.com.fiap.adj.techchallenge.getaf.dto.TicketDTO;
import br.com.fiap.adj.techchallenge.getaf.dto.UpdateTicketDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Ticket;
import br.com.fiap.adj.techchallenge.getaf.service.impl.TicketServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketServiceImpl service;

    @PostMapping
    public ResponseEntity<Ticket> saveTicket(@RequestBody @Valid TicketDTO ticketDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTicket(ticketDTO));

    }

    @GetMapping
    public ResponseEntity<Page<Ticket>> getAllTickets(
            @PageableDefault(page = 0, size = 10, sort = "nome") Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTickets(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTicket(@PathVariable(value = "id") Long id){
        Optional<Ticket> ticket = service.getTicket(id);
        if(ticket.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticket.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTicket(@PathVariable(value = "id") Long id,
                                               @RequestBody @Valid UpdateTicketDTO updateTicketDTO){
        Optional<Ticket> ticket = service.updateTicket(id, updateTicketDTO);
        if(ticket.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticket.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable(value = "id") Long id) throws EntityNotFoundException {
        try {
            service.deleteTicket(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket não Encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Ticket deletado com sucesso.");
    }



}
