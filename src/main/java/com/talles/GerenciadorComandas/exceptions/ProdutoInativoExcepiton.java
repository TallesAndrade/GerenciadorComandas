package com.talles.GerenciadorComandas.exceptions;

public class ProdutoInativoExcepiton extends RuntimeException {

    public ProdutoInativoExcepiton(String message) {
        super(message);
    }

    public ProdutoInativoExcepiton() {
        super("Produto inativo no sistema");
    }
}
