package com.talles.GerenciadorComandas.enums;

public enum Status {
    ABERTA(0),FECHADA(1),CANCELADA(2);

    private final int value;
    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
