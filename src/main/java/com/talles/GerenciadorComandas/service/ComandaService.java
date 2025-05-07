package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ComandaDTO;
import com.talles.GerenciadorComandas.controller.dtos.ItemComandaDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.enums.Status;
import com.talles.GerenciadorComandas.mapper.ComandaMapper;
import com.talles.GerenciadorComandas.repository.ComandaRepository;
import com.talles.GerenciadorComandas.repository.ProdutoComandaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final ComandaMapper comandaMapper;
    private final ProdutoComandaService produtoComandaService;
    private final ProdutoComandaRepository produtoComandaRepository;


    public ComandaService(ComandaRepository comandaRepository, ComandaMapper comandaMapper, ProdutoComandaService produtoComandaService, ProdutoComandaRepository produtoComandaRepository) {
        this.comandaRepository = comandaRepository;
        this.comandaMapper = comandaMapper;
        this.produtoComandaService = produtoComandaService;
        this.produtoComandaRepository = produtoComandaRepository;
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

    public void removerProduto(Long idComanda,Long idProduto, int quantidade){
        Comanda comanda = comandaRepository.findById(idComanda).orElseThrow();
        List<ProdutoComanda> produtosComanda = comanda.getProdutosComanda();
        Iterator<ProdutoComanda> iterator = produtosComanda.iterator();
        while (iterator.hasNext()){
            ProdutoComanda produtoComanda = iterator.next();
            if (Objects.equals(produtoComanda.getProduto().getId(), idProduto)){
                produtoComandaService.devolverProdutoEstoque(produtoComanda, quantidade);
                iterator.remove();
                produtoComandaRepository.delete(produtoComanda);

            }
        }
        comanda.setProdutosComanda(produtosComanda);
        comandaRepository.save(comanda);
    }

    public List<ComandaDTO> listarComandas(){
        List<Comanda> comandas = comandaRepository.findByStatusComanda(Status.ABERTA);
        List<ComandaDTO> comandasDTOS = comandas.stream()
                .map(comandaMapper::mapToDto).toList();
        return comandasDTOS;
    }

    public ComandaDTO comandaPorID(Long idComanda){
        Comanda comanda = comandaRepository.findById(idComanda).orElseThrow();
        return comandaMapper.mapToDto(comanda);
    }
}
