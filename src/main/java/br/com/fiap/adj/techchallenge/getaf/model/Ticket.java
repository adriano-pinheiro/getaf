package br.com.fiap.adj.techchallenge.getaf.model;

import br.com.fiap.adj.techchallenge.getaf.model.enums.PrioridadeTicket;
import br.com.fiap.adj.techchallenge.getaf.model.enums.StatusTicket;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private PrioridadeTicket prioridade;
    private StatusTicket status;
    private Long idCliente;
    private Integer horasOrcadas;

    public Ticket() {
    }

    public Ticket(Long id, String nome, String descricao, PrioridadeTicket prioridade, StatusTicket status, Long idCliente, Integer horasOrcadas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.idCliente = idCliente;
        this.horasOrcadas = horasOrcadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PrioridadeTicket getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeTicket prioridade) {
        this.prioridade = prioridade;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getHorasOrcadas() {
        return horasOrcadas;
    }

    public void setHorasOrcadas(Integer horasOrcadas) {
        this.horasOrcadas = horasOrcadas;
    }
}
