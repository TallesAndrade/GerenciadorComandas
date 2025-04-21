package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ComandaDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.mapper.ComandaMapper;
import com.talles.GerenciadorComandas.repository.ComandaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final ComandaMapper comandaMapper;
    private final ProdutoComandaService produtoComandaService;


    public ComandaService(ComandaRepository comandaRepository, ComandaMapper comandaMapper, ProdutoComandaService produtoComandaService) {
        this.comandaRepository = comandaRepository;
        this.comandaMapper = comandaMapper;
        this.produtoComandaService = produtoComandaService;
    }

    public ComandaDTO criarComanda(ComandaDTO comandaDTO){
        Comanda comanda = comandaMapper.mapToEntity(comandaDTO);
        comanda.setDataAbertura(LocalDateTime.now());
        comandaRepository.save(comanda);
        return comandaMapper.mapToDto(comanda);
    }
    public ComandaDTO adicionarProduto(Long idComanda, Long idProduto, int quantidade){
        Comanda comanda = comandaRepository.findById(idComanda).orElseThrow();
        List<ProdutoComanda> produtosComanda = comanda.getProdutosComanda();
        if (produtosComanda == null){
            produtosComanda = new ArrayList<>();
        }
        ProdutoComanda produtoComanda = produtoComandaService.adicionarProdutoComanda(comanda, idProduto, quantidade);
        produtosComanda.add(produtoComanda);
        comanda.setProdutosComanda(produtosComanda);
        comandaRepository.save(comanda);
        return comandaMapper.mapToDto(comanda);
    }
}
