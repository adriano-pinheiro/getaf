package br.com.fiap.adj.techchallenge.getaf.dto;

public record EnderecoDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String cidade
) {
}
