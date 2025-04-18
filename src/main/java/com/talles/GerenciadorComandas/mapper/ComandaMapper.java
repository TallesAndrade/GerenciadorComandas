package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.ComandaDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ComandaMapper {

    public ComandaDTO mapToDto(Comanda comanda){
        ComandaDTO comandaDTO = new ComandaDTO();
        comandaDTO.setId(comanda.getId());
        comandaDTO.setNomeCliente(comanda.getNomeCliente());
        comandaDTO.setProdutosComanda(comanda.getProdutosComanda());
        comandaDTO.setValorTotal(comanda.getValorTotal());
        comandaDTO.setDataAbertura(comanda.getDataAbertura());
        comandaDTO.setStatusComanda(comanda.getStatusComanda());
        return comandaDTO;
    }

    public Comanda mapToEntity(ComandaDTO comandaDTO){
        Comanda comanda = new Comanda();
        comanda.setId(comandaDTO.getId());
        comanda.setNomeCliente(comandaDTO.getNomeCliente());
        comanda.setProdutosComanda(comandaDTO.getProdutosComanda());
        comanda.setValorTotal(comandaDTO.getValorTotal());
        comanda.setDataAbertura(comandaDTO.getDataAbertura());
        comanda.setStatusComanda(comandaDTO.getStatusComanda());
        return comanda;
    }
}
