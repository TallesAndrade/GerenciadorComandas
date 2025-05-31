package com.talles.GerenciadorComandas.mapper;

import com.talles.GerenciadorComandas.controller.dtos.ComandaFechadaResponseDTO;
import com.talles.GerenciadorComandas.controller.dtos.ComandaResponseDTO;
import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.util.DataParser;
import org.springframework.stereotype.Component;

@Component
public class ComandaResponseMapper {
    private final DataParser dataParser;
    private final ProdutoComandaMapper produtoComandaMapper;

    public ComandaResponseMapper(DataParser dataParser,ProdutoComandaMapper produtoComandaMapper) {
        this.dataParser = dataParser;
        this.produtoComandaMapper = produtoComandaMapper;
    }

    public ComandaResponseDTO toComandaResponseDTO(Comanda comanda) {
        ComandaResponseDTO comandaResponseDTO = new ComandaResponseDTO();
        comandaResponseDTO.setId(comanda.getId());
        comandaResponseDTO.setNomeCliente(comanda.getNomeCliente());
        comandaResponseDTO.setProdutosComanda(comanda.getProdutosComanda().stream().map(produtoComandaMapper::toDTO).toList());
        comandaResponseDTO.setValorTotal(comanda.getValorTotal());
        comandaResponseDTO.setDataAbertura(dataParser.format(comanda.getDataAbertura()));
        comandaResponseDTO.setStatusComanda(comanda.getStatusComanda());
        return comandaResponseDTO;
    }

    public ComandaFechadaResponseDTO toComandaFechadaResponseDTO(Comanda comanda) {
        ComandaFechadaResponseDTO comandaFechadaResponseDTO = new ComandaFechadaResponseDTO();
        comandaFechadaResponseDTO.setId(comanda.getId());
        comandaFechadaResponseDTO.setNomeCliente(comanda.getNomeCliente());
        comandaFechadaResponseDTO.setProdutosComanda(comanda.getProdutosComanda().stream().map(produtoComandaMapper::toDTO).toList());
        comandaFechadaResponseDTO.setValorTotal(comanda.getValorTotal());
        comandaFechadaResponseDTO.setDataAbertura(dataParser.format(comanda.getDataAbertura()));
        comandaFechadaResponseDTO.setDataFechamento(dataParser.format(comanda.getDataFechamento()));
        comandaFechadaResponseDTO.setStatusComanda(comanda.getStatusComanda());
        return comandaFechadaResponseDTO;
    }
}
