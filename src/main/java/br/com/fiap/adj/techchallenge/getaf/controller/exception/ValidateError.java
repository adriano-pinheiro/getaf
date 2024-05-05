package br.com.fiap.api.usuarios_pettech.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError{
    private List<ValidateMessage> mensagens = new ArrayList<ValidateMessage>();

    public List<ValidateMessage> getMensagens() {
        return mensagens;
    }

    public void addMensagens(String campo, String mensagem) {
        mensagens.add(new ValidateMessage(campo, mensagem));
    }

}
