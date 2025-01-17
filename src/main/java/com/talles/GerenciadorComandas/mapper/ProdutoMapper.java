package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoDTO;
import com.talles.GerenciadorComandas.entity.Produto;

public class ProdutoMapper {
    public Produto mapToEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCodigoDeBarras(produtoDTO.getCodigoDeBarras());
        produto.setProdutoComandas(produtoDTO.getProdutoComandas());
        return produto;
    }
    public ProdutoDTO mapToDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setCodigoDeBarras(produto.getCodigoDeBarras());
        produtoDTO.setProdutoComandas(produto.getProdutoComandas());
        return produtoDTO;
    }
}
