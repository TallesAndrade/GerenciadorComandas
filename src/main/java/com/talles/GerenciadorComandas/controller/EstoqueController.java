package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.EstoqueDTO;
import com.talles.GerenciadorComandas.service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }


    @PostMapping("/{id}/adicionarsaldo")
    public ResponseEntity<EstoqueDTO> adicionarSaldoEstoque(@PathVariable Long id,@RequestBody EstoqueDTO estoqueDTO){
     return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.adicionarQuantidadeEstoque(id, estoqueDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> buscarEstoquePorProduto(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listarProdutoEstoque(id));
    }
    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> listarEstoque(){
       return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listarEstoque());
    }
}
