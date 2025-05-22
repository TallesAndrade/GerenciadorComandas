package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.ProdutoResponseDTO;
import com.talles.GerenciadorComandas.entity.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    Produto toEntity(ProdutoRequestDTO requestDTO);

    ProdutoResponseDTO toDTO(Produto produto);

}
