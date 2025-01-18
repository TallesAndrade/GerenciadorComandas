package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoDTO;
import com.talles.GerenciadorComandas.entity.Produto;
import com.talles.GerenciadorComandas.mapper.ProdutoMapper;
import com.talles.GerenciadorComandas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }


    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO){
        Produto produto = produtoMapper.mapToEntity(produtoDTO);
        produtoRepository.save(produto);
        return produtoMapper.mapToDTO(produto);
    }

    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtosList = produtoRepository.findAll();
        List<ProdutoDTO> produtos = produtosList.stream()
                .map(produtoMapper::mapToDTO).toList();

        return produtos;

    }
    public ProdutoDTO produtoById(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow();
        return produtoMapper.mapToDTO(produto);
    }
    public void deletarProdutoById(Long id){
        produtoRepository.deleteById(id);

    }


}
