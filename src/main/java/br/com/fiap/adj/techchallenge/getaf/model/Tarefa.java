package br.com.fiap.adj.techchallenge.getaf.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_tarefas")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idTicket;

    private String nome;

    private String status;

    private String executor;


    public Tarefa(Long id,
                   Long idTicket,
                   String nome,
                   String status,
                   String executor,
                   ) {
        this.id = id;
        this.idTicket = idTicket;
        this.nome = nome;
        this.status = status;
        this.executor = executor;
    }

    public Tarefa() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }


    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", status='" + status + '\'' +
                ", executor='" + executor + '\'' +
                '}';
    }
}
