package br.com.fiap.adj.techchallenge.getaf.model.enums;

public enum PrioridadeTicket {
    MUITO_BAIXA(1),
    BAIXA(2),
    MEDIA(3),
    ALTA(4),
    MUITO_ALTA(5),
    URGENTE(6);

    private final Integer prioridade;

    PrioridadeTicket(Integer prioridade) { this.prioridade = prioridade; }

    public Integer getPrioridade() { return prioridade; }
}
