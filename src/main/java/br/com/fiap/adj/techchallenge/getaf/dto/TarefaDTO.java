package br.com.fiap.adj.techchallenge.getaf.dto;

import jakarta.validation.constraints.NotBlank;

public record TarefaDTO(
        Long id,
        
        @NotBlank@NotBlank(message = "o id do ticket é obrigatório.")
        Long idTicket,

        @NotBlank(message = "o nome da tarefa é obrigatório.")
        String nome,

        String status,

        String executor
) {
}
