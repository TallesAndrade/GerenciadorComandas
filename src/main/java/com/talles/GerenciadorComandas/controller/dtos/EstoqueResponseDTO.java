package com.talles.GerenciadorComandas.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueResponseDTO {
    private Long id;
    private String nomeProduto;
    private Long idProduto;
    private int quantidade;
}
