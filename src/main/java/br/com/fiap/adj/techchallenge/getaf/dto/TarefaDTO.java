package br.com.fiap.adj.techchallenge.getaf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefaDTO(
        Long id,
        
        @NotNull(message = "o id do ticket é obrigatório.")
        Long idTicket,

        @NotBlank(message = "o nome da tarefa é obrigatório.")
        String nome,

        String status,

        String executor
) {
}
