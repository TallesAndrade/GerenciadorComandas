package com.talles.GerenciadorComandas.exceptions;

public class QuantidadeInsuficienteException extends RuntimeException {
    public QuantidadeInsuficienteException(String message) {
        super(message);
    }

    public QuantidadeInsuficienteException() {
        super("Quantidade disponivel insuficiente");
    }
}
