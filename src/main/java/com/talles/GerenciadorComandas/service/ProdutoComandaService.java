package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.entity.Produto;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.exceptions.ProdutoNotFoundException;
import com.talles.GerenciadorComandas.repository.ProdutoComandaRepository;
import com.talles.GerenciadorComandas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoComandaService {
    private final ProdutoComandaRepository produtoComandaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueService estoqueService;


    public ProdutoComandaService(ProdutoComandaRepository produtoComandaRepository,ProdutoRepository produtoRepository, EstoqueService estoqueService) {
        this.produtoComandaRepository = produtoComandaRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueService = estoqueService;
    }

    @Transactional
    public ProdutoComanda adicionarProdutoComanda(Comanda comanda,Long idProduto, int quantidade){
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(ProdutoNotFoundException::new);
        estoqueService.subtrairSaldo(idProduto, quantidade);
        return produtoComandaRepository.save(new ProdutoComanda(comanda,produto,quantidade));

    }

    public void voltarEstoque(Comanda comanda) {
        List<ProdutoComanda> produtos = comanda.getProdutosComanda();
        for(ProdutoComanda produtoComanda : produtos) {
            Produto produto = produtoComanda.getProduto();
            estoqueService.adicionarSaldo(produto.getId(), produtoComanda.getQuantidade());
        }

    }

    public void devolverProdutoEstoque(ProdutoComanda produtoComanda,int quantidade) {
        Produto produto = produtoComanda.getProduto();
        estoqueService.adicionarSaldo(produto.getId() , quantidade);
    }
}
