package com.talles.GerenciadorComandas.infra;

import com.talles.GerenciadorComandas.exceptions.EstoqueAindaDisponivelException;
import com.talles.GerenciadorComandas.exceptions.EstoqueProdutoNotFoundException;
import com.talles.GerenciadorComandas.exceptions.ProdutoNotFoundException;
import com.talles.GerenciadorComandas.exceptions.QuantidadeInsuficienteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<String> produtoNotFoundHandler(ProdutoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EstoqueProdutoNotFoundException.class)
    public ResponseEntity<String> estoqueProdutoNotFoundHandler(EstoqueProdutoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(QuantidadeInsuficienteException.class)
    public ResponseEntity<String> quantidadeInsuficienteHandler(QuantidadeInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(EstoqueAindaDisponivelException.class)
    public ResponseEntity<String> estoqueAIndaDisponivelHandler(EstoqueAindaDisponivelException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }
}
