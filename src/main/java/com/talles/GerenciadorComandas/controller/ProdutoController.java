package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.ProdutoResponseDTO;
import com.talles.GerenciadorComandas.docs.ProdutoApiDocs;
import com.talles.GerenciadorComandas.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController implements ProdutoApiDocs {
    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/criar")
    @Override
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@Valid @RequestBody ProdutoRequestDTO produtoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(produtoDTO));
    }

    @GetMapping("/listar")
    @Override
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos());
    }

    @GetMapping("{id}")
    @Override
    public ResponseEntity<ProdutoResponseDTO> procurarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.produtoById(id));
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        produtoService.deletarProdutoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("{id}")
    @Override
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id,@Valid @RequestBody ProdutoRequestDTO produtoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id,produtoDTO));
    }
}
