package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.repository.ProdutoComandaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoComandaService {
    private final ProdutoComandaRepository produtoComandaRepository;

    public ProdutoComandaService(ProdutoComandaRepository produtoComandaRepository) {
        this.produtoComandaRepository = produtoComandaRepository;
    }
}
