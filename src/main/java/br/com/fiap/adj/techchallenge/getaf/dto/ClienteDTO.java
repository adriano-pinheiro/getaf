package br.com.fiap.adj.techchallenge.getaf.dto;

import br.com.fiap.adj.techchallenge.getaf.model.Endereco;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

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

        List<EnderecoDTO> enderecos
) {
}
