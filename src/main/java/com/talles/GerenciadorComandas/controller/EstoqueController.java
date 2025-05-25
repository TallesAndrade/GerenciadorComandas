package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.EstoqueRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.EstoqueResponseDTO;
import com.talles.GerenciadorComandas.service.EstoqueService;
import jakarta.validation.Valid;
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


    @PatchMapping("/{id}/adicionarsaldo")
    public ResponseEntity<EstoqueResponseDTO> adicionarSaldoEstoque(@PathVariable Long id,@Valid @RequestBody EstoqueRequestDTO estoqueDTO){
     return ResponseEntity.status(HttpStatus.OK).body(estoqueService.adicionarQuantidadeEstoque(id, estoqueDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarPorProdutoId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.buscarEstoquePorProdutoId(id));
    }
    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> buscarTodos(){
       return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listarEstoque());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        estoqueService.deletarEstoque(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/removersaldo")
    public ResponseEntity<EstoqueResponseDTO> removerSaldo(@PathVariable long id, @Valid @RequestBody EstoqueRequestDTO estoqueDTO){
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.subtrairQuantidadeEstoque(id,estoqueDTO.getQuantidade()));
    }
}
