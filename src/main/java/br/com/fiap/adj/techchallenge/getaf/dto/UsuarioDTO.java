package br.com.fiap.adj.techchallenge.getaf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,
        @NotBlank(message = "O login não pode estar em branco.")
        String login,
        @Email(message="E-mail inválido.")
        String email,
        @CPF(message = "CPF inválido.")
        String cpf,
        LocalDate dataNascimento,
        @NotBlank(message = "A senha não pode estar em branco.")
        String password
){}
