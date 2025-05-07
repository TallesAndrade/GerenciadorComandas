package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.ComandaDTO;
import com.talles.GerenciadorComandas.controller.dtos.ItemComandaDTO;
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

    @PostMapping("/criar")
    public ResponseEntity<ComandaDTO> criarComanda(@RequestBody ComandaDTO comandaDTO){
        return ResponseEntity.ok(comandaService.criarComanda(comandaDTO));

    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity<ComandaDTO> adicionarProduto(@RequestBody ItemComandaDTO dto,@PathVariable Long id){
        return ResponseEntity.ok(comandaService.adicionarProduto(id, dto.getIdProduto(), dto.getQuantidade()));
    }

    @PatchMapping("/{id}/fechar")
    public ResponseEntity<ComandaDTO> fecharComanda(@PathVariable Long id){
        return ResponseEntity.ok(comandaService.fecharComanda(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarComanda(@PathVariable Long id){
        comandaService.cancelarComanda(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/editar")
    public ResponseEntity<ComandaDTO> editarProduto(@RequestBody ItemComandaDTO dto){
        return ResponseEntity.ok(comandaService.editarComanda(dto));
    }

    @DeleteMapping("/{idComanda}/deletarProduto/{idProduto}?quantidade=x")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long idComanda,@PathVariable Long idProduto,@RequestParam int quantidade){
        comandaService.removerProduto(idComanda, idProduto, quantidade);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/comandaPorId")
    public ResponseEntity<ComandaDTO> buscarComandaPorId(@PathVariable Long id){
        return ResponseEntity.ok(comandaService.comandaPorID(id));

    }

    @GetMapping("/comandas")
    public ResponseEntity<List<ComandaDTO>> listarComandas(){
        return ResponseEntity.ok(comandaService.listarComandas());
    }
}
