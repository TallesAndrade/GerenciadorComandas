package com.talles.GerenciadorComandas.controller.dtos;

import lombok.Data;

@Data
public class AdicionarProdutoDTO {
    private Long idComanda;
    private Long idProduto;
    private int quantidade;
}
