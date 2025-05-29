package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.*;
import com.talles.GerenciadorComandas.service.ComandaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comandas")
public class ComandaController {
    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @PostMapping()
    public ResponseEntity<ComandaResponseDTO> criarComanda(@RequestBody ComandaRequestDTO comandaDTO){
        return ResponseEntity.ok(comandaService.criarComanda(comandaDTO));

    }

    @PostMapping("/{id}/produtos")
    public ResponseEntity<ComandaResponseDTO> adicionarProduto(@PathVariable Long id,@RequestBody ItemComandaDTO dto){
        return ResponseEntity.ok(comandaService.adicionarProduto(id , dto.getIdProduto(), dto.getQuantidade()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ComandaFechadaResponseDTO> fecharComanda(@PathVariable Long id,@RequestBody StatusComandaRequestDTO dto){
        return ResponseEntity.ok(comandaService.ajustarStatusComanda(id,dto.status()));
    }



    @PatchMapping("/{id}/itens")
    public ResponseEntity<ComandaResponseDTO> editarProduto(@PathVariable Long id , @RequestBody ItemComandaDTO dto){
        return ResponseEntity.ok(comandaService.editarComanda(id,dto));
    }

    @DeleteMapping("/{idComanda}/itens/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long idComanda,@PathVariable Long idProduto){
        comandaService.removerProduto(idComanda, idProduto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComandaResponseDTO> buscarComandaPorId(@PathVariable Long id){
        return ResponseEntity.ok(comandaService.comandaPorID(id));

    }

    @GetMapping()
    public ResponseEntity<List<ComandaResponseDTO>> listarComandas(){
        return ResponseEntity.ok(comandaService.listarComandas());
    }
}
