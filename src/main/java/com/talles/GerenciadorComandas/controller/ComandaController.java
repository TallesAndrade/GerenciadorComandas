package com.talles.GerenciadorComandas.controller;

import com.talles.GerenciadorComandas.controller.dtos.ComandaDTO;
import com.talles.GerenciadorComandas.service.ComandaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
