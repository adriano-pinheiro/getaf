package br.com.fiap.adj.techchallenge.getaf.controller.exception;

public class ValidateMessage {

    private String campo;
    private String mensagem;

    public ValidateMessage(){}

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ValidateMessage(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
}
