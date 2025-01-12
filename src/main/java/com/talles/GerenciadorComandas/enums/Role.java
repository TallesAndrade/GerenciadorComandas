package com.talles.GerenciadorComandas.model;

public enum Role {
    ADMINISTRACAO(1),FUNCIONARIO(2);

    private final int valor;

    Role(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

}
