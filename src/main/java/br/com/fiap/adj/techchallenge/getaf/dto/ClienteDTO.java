package br.com.fiap.adj.techchallenge.getaf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record ClienteDTO(
        Long id,

        @NotBlank(message = "o nome é obrigatório.")
        String nome,

        @NotBlank(message = "CNPJ obrigatório.")
        @CNPJ(message = "Favor digitar um CNPJ válido.")
        String cnpj,

        @NotBlank(message = "o e-mail é obrigatório.")
        @Email(message = "Favor digitar um e-mail válido.")
        String email,

        String telefone,

        String endereco,

        String complemento,

        String cep,

        String bairro,

        String cidade,

        String uf
) {
}
