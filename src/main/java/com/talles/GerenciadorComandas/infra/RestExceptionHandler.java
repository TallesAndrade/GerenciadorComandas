package com.talles.GerenciadorComandas.infra;

import com.talles.GerenciadorComandas.exceptions.*;
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

    @ExceptionHandler(ComandaNotFoundException.class)
    public ResponseEntity<String> comandaNotFoundHandler(ComandaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(StatusComandaInvalidoException.class)
    public ResponseEntity<String> statusComandaInvalidoHandler(StatusComandaInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoInativoExcepiton.class)
    public ResponseEntity<String> produtoInativoHandler(ProdutoInativoExcepiton ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }
}
