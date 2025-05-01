package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ComandaDTO;
import com.talles.GerenciadorComandas.controller.dtos.ItemComandaDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.enums.Status;
import com.talles.GerenciadorComandas.mapper.ComandaMapper;
import com.talles.GerenciadorComandas.repository.ComandaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        comanda.setStatusComanda(Status.ABERTA);
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
        atualizarValorComanda(comanda);
        comandaRepository.save(comanda);
        return comandaMapper.mapToDto(comanda);
    }

    private void atualizarValorComanda(Comanda comanda){
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoComanda produtoComanda : comanda.getProdutosComanda()) {
            valorTotal = valorTotal.add(produtoComanda.getValorTotal());
        }
        comanda.setValorTotal(valorTotal);
    }

    public ComandaDTO fecharComanda(Long idComanda){
        Comanda comanda = comandaRepository.findById(idComanda).orElseThrow();
        comanda.setStatusComanda(Status.FECHADA);
        comandaRepository.save(comanda);
        return comandaMapper.mapToDto(comanda);
    }

    public void cancelarComanda(Long idComanda){
        Comanda comanda = comandaRepository.findById(idComanda).orElseThrow();
        comanda.setStatusComanda(Status.CANCELADA);
        produtoComandaService.voltarEstoque(comanda);
        comandaRepository.save(comanda);
    }

    public ComandaDTO editarComanda(ItemComandaDTO itemComanda){
        Comanda comanda = comandaRepository.findById(itemComanda.getIdComanda()).orElseThrow();
        List<ProdutoComanda> produtosComanda = comanda.getProdutosComanda();
        for (ProdutoComanda produtoComanda : produtosComanda){
            if (Objects.equals(produtoComanda.getProduto().getId(), itemComanda.getIdProduto())){
                ajustarQuantidade(produtoComanda,itemComanda.getQuantidade());

            }
        }
        comanda.setProdutosComanda(produtosComanda);
        atualizarValorComanda(comanda);
        comandaRepository.save(comanda);
        return comandaMapper.mapToDto(comanda);
    }

    private void ajustarQuantidade(ProdutoComanda produtoComanda, int quantidade) {
        int diferenca = Math.abs(produtoComanda.getQuantidade() - quantidade);
        if (produtoComanda.getQuantidade() > quantidade){
            produtoComandaService.devolverProdutoEstoque(produtoComanda,diferenca);
        }else {
            produtoComanda.setQuantidade(quantidade);
        }
    }

    public void removerProduto(ItemComandaDTO itemComanda){
        Comanda comanda = comandaRepository.findById(itemComanda.getIdComanda()).orElseThrow();
        List<ProdutoComanda> produtosComanda = comanda.getProdutosComanda();
        for (ProdutoComanda produtoComanda : produtosComanda){
            if (Objects.equals(produtoComanda.getProduto().getId(), itemComanda.getIdProduto())){
                produtoComandaService.devolverProdutoEstoque(produtoComanda, itemComanda.getQuantidade());
                produtosComanda.remove(produtoComanda);
            }
        }
        comanda.setProdutosComanda(produtosComanda);
        comandaRepository.save(comanda);
    }

    public List<Comanda> listarComandas(){
        return comandaRepository.findByStatusComanda(Status.ABERTA);
    }
}
