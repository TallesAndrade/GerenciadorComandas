package com.talles.GerenciadorComandas.exceptions;



public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String message) {
        super(message);
    }

    public ProdutoNotFoundException() {
        super("Produto não encontrado");
    }

  
}
