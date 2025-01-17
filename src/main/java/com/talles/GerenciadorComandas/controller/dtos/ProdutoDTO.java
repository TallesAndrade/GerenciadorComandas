package com.talles.GerenciadorComandas.controller.dtos;

import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;


    private String nome;
    private BigDecimal preco;

    private String codigoDeBarras;

    private List<ProdutoComanda> produtoComandas = new ArrayList<>();
}
