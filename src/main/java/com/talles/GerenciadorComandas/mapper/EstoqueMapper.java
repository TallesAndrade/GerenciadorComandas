package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.EstoqueDTO;
import com.talles.GerenciadorComandas.entity.Estoque;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public Estoque mapToEntity(EstoqueDTO estoqueDTO){
        Estoque estoque = new Estoque();
        estoque.setId(estoqueDTO.getId());
        estoque.setProduto(estoqueDTO.getProduto());
        estoque.setQuantidade(estoqueDTO.getQuantidade());
        return estoque;
    }

    public EstoqueDTO mapToDTO(Estoque estoque){
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setId(estoque.getId());
        estoqueDTO.setProduto(estoque.getProduto());
        estoqueDTO.setQuantidade(estoque.getQuantidade());
        return estoqueDTO;
    }
}
