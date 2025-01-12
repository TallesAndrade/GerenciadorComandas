package com.talles.GerenciadorComandas.model;

public enum Status {
    ABERTA(0),FECHADA(1),CANCELAD(2);

    private final int value;
    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
