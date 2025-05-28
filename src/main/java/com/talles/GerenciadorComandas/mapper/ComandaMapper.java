package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.ComandaFechadaResponseDTO;
import com.talles.GerenciadorComandas.controller.dtos.ComandaRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.ComandaResponseDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring" , uses = ProdutoComandaMapper.class)
public interface ComandaMapper {
    ComandaResponseDTO ToDto(Comanda comanda);

    Comanda ToEntity(ComandaRequestDTO comandaDTO);

    ComandaFechadaResponseDTO toFechadaDTO(Comanda comanda);
}
