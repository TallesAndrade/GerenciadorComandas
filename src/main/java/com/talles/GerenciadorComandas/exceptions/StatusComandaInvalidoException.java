package com.talles.GerenciadorComandas.exceptions;

public class StatusComandaInvalidoException extends RuntimeException {
    public StatusComandaInvalidoException(String message) {
        super(message);
    }

    public StatusComandaInvalidoException() {
        super("Status invalido");
    }
}
