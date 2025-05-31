package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.ComandaRequestDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring" , uses = ProdutoComandaMapper.class)
public interface ComandaMapper {

    Comanda ToEntity(ComandaRequestDTO comandaDTO);

}
