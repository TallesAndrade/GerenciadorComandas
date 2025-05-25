package com.talles.GerenciadorComandas.exceptions;

public class EstoqueAindaDisponivelException extends RuntimeException {
    public EstoqueAindaDisponivelException(String message) {
        super(message);
    }

    public EstoqueAindaDisponivelException() {
        super("Não é possivel excluir estoque: Ainda há unidades disponiveis. Zere o estoque antes de prosseguir");
    }
}
