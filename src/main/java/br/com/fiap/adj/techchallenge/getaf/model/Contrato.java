package br.com.fiap.adj.techchallenge.getaf.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="tb_contratos")
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private int horas;

    private BigDecimal valor;

    private LocalDate dtInicioVigencia;

    private LocalDate dtFimVigencia;

    private String observacao;

    public Contrato(){}

    public Contrato(Long id,
                    Cliente cliente,
                    int horas,
                    BigDecimal valor,
                    LocalDate dtInicioVigencia,
                    LocalDate dtFimVigencia,
                    String observacao) {
        this.id = id;
        this.cliente = cliente;
        this.horas = horas;
        this.valor = valor;
        this.dtInicioVigencia = dtInicioVigencia;
        this.dtFimVigencia = dtFimVigencia;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDtInicioVigencia() {
        return dtInicioVigencia;
    }

    public void setDtInicioVigencia(LocalDate dtInicioVigencia) {
        this.dtInicioVigencia = dtInicioVigencia;
    }

    public LocalDate getDtFimVigencia() {
        return dtFimVigencia;
    }

    public void setDtFimVigencia(LocalDate dtFimVigencia) {
        this.dtFimVigencia = dtFimVigencia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return Objects.equals(id, contrato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, horas, valor, dtInicioVigencia, dtFimVigencia, observacao);
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", horas=" + horas +
                ", valor=" + valor +
                ", dtInicioVigencia=" + dtInicioVigencia +
                ", dtFimVigencia=" + dtFimVigencia +
                ", observacao='" + observacao + '\'' +
                '}';
    }

}
