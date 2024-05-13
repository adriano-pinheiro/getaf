package br.com.fiap.adj.techchallenge.getaf.dto;

import br.com.fiap.adj.techchallenge.getaf.model.enums.PrioridadeTicket;
import br.com.fiap.adj.techchallenge.getaf.model.enums.StatusTicket;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketDTO(@NotNull String nome, String descricao, @NotNull PrioridadeTicket prioridade, @NotNull StatusTicket status, Integer horasOrcadas) {
}
