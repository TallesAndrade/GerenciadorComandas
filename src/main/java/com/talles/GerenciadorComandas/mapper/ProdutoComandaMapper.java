package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoComandaResponseDTO;
import com.talles.GerenciadorComandas.controller.dtos.ProdutoResponseDTO;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = ProdutoResponseDTO.class)
public interface ProdutoComandaMapper {

    ProdutoComandaResponseDTO toDTO(ProdutoComanda produto);
}
