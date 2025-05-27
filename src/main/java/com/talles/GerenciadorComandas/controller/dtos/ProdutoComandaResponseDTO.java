package com.talles.GerenciadorComandas.controller.dtos;


import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoComandaResponseDTO {

    private Long id;
    private Comanda comanda;
    private Produto produto;
    private int quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}
