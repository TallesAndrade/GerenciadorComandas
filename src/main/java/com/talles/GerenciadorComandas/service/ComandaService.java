package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ComandaDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.mapper.ComandaMapper;
import com.talles.GerenciadorComandas.repository.ComandaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final ComandaMapper comandaMapper;

    public ComandaService(ComandaRepository comandaRepository,ComandaMapper comandaMapper) {
        this.comandaRepository = comandaRepository;
        this.comandaMapper = comandaMapper;
    }

    public ComandaDTO criarComanda(ComandaDTO comandaDTO){
        Comanda comanda = comandaMapper.mapToEntity(comandaDTO);
        comanda.setDataAbertura(LocalDateTime.now());
        comandaRepository.save(comanda);
        return comandaMapper.mapToDto(comanda);
    }
    public ComandaDTO adicionarProduto(Long id,ProdutoComanda produtoComanda){
        Comanda comanda = comandaRepository.findById(id).orElseThrow();
        comanda.pro;

    }
}
