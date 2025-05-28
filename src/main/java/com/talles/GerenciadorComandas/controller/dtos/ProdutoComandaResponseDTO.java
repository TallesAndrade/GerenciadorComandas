package com.talles.GerenciadorComandas.controller.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoComandaResponseDTO {

    private Long id;
    private ProdutoResponseDTO produto;
    private int quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}
