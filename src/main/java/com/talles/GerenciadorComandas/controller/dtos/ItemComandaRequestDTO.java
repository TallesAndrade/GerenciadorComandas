package com.talles.GerenciadorComandas.controller.dtos;

import lombok.Data;

@Data
public class ItemComandaRequestDTO {
    private Long idProduto;
    private int quantidade;
}
