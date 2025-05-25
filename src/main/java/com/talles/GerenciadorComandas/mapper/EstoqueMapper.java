package com.talles.GerenciadorComandas.mapper;


import com.talles.GerenciadorComandas.controller.dtos.EstoqueResponseDTO;
import com.talles.GerenciadorComandas.entity.Estoque;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {

    EstoqueResponseDTO toDTO(Estoque estoque);
}
