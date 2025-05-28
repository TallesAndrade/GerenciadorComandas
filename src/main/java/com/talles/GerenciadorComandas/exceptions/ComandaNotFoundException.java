package com.talles.GerenciadorComandas.exceptions;

public class ComandaNotFoundException extends RuntimeException {
    public ComandaNotFoundException(String message) {
        super(message);
    }

    public ComandaNotFoundException() {
        super("Comanda não encotrada");
    }
}
