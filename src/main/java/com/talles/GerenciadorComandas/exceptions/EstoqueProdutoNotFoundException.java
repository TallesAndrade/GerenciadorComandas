package com.talles.GerenciadorComandas.exceptions;

public class EstoqueProdutoNotFoundException extends RuntimeException {


    public EstoqueProdutoNotFoundException(String message) {
        super(message);
    }

    public EstoqueProdutoNotFoundException() {
        super("Estoque não encontrado");
    }


}
