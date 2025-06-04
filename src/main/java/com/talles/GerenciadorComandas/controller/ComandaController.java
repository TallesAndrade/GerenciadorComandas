package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.*;
import com.talles.GerenciadorComandas.docs.ComandaApiDocs;
import com.talles.GerenciadorComandas.service.ComandaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comandas")
public class ComandaController implements ComandaApiDocs{
    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @PostMapping
    @Override
    public ResponseEntity<ComandaResponseDTO> criarComanda(@Valid @RequestBody ComandaRequestDTO comandaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comandaService.criarComanda(comandaDTO));
    }

    @PostMapping("/{id}/produtos")
    @Override
    public ResponseEntity<ComandaResponseDTO> adicionarProduto(@PathVariable Long id, @Valid@RequestBody ItemComandaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comandaService.adicionarProduto(id, dto.getIdProduto(), dto.getQuantidade()));
    }

    @PatchMapping("/{id}/status")
    @Override
    public ResponseEntity<ComandaFechadaResponseDTO> ajustarStatusComanda(@PathVariable Long id,@Valid @RequestBody StatusComandaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comandaService.ajustarStatusComanda(id, dto.status()));
    }

    @PatchMapping("/{id}/itens")
    @Override
    public ResponseEntity<ComandaResponseDTO> editarProduto(@PathVariable Long id,@Valid @RequestBody ItemComandaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comandaService.editarComanda(id, dto));
    }

    @DeleteMapping("/{idComanda}/itens/{idProduto}")
    @Override
    public ResponseEntity<Void> deletarProduto(@PathVariable Long idComanda, @PathVariable Long idProduto) {
        comandaService.removerProduto(idComanda, idProduto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ComandaResponseDTO> buscarComandaPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comandaService.comandaPorID(id));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ComandaResponseDTO>> listarComandas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comandaService.listarComandas());
    }
}