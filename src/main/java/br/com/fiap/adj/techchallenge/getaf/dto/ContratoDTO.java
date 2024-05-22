package br.com.fiap.adj.techchallenge.getaf.dto;

import br.com.fiap.adj.techchallenge.getaf.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratoDTO(
        Long id,

        Long id_cliente,

        int horas,

        BigDecimal valor,

        //@NotBlank(message = "Informar o início de vigência do contrato.")
        LocalDate dtInicioVigencia,

        LocalDate dtFimVigencia,

        String observacao

) {}
