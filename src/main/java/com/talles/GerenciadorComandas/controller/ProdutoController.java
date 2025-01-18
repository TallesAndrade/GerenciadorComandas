package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoDTO;
import com.talles.GerenciadorComandas.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> procurarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.produtoById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        produtoService.deletarProdutoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
