package com.talles.GerenciadorComandas.controller.dtos;

import lombok.Data;

@Data
public class ItemComandaDTO {
    private Long idComanda;
    private Long idProduto;
    private int quantidade;
}
