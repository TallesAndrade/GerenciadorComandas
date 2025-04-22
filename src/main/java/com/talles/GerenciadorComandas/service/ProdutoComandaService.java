package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.entity.Estoque;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.repository.EstoqueRepository;
import com.talles.GerenciadorComandas.repository.ProdutoComandaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoComandaService {
    private final ProdutoComandaRepository produtoComandaRepository;
    private final EstoqueRepository estoqueRepository;

    public ProdutoComandaService(ProdutoComandaRepository produtoComandaRepository, EstoqueRepository estoqueRepository) {
        this.produtoComandaRepository = produtoComandaRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public ProdutoComanda adicionarProdutoComanda(Comanda comanda,Long idProduto, int quantidade){
        Estoque produtoEstoque =estoqueRepository.findById(idProduto).orElseThrow();
        if (produtoEstoque.getQuantidade() >= quantidade){
            produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() - quantidade);
            estoqueRepository.save(produtoEstoque);
            return produtoComandaRepository.save(new ProdutoComanda(comanda,produtoEstoque.getProduto(),quantidade));
        }
        return null;
    }
}
