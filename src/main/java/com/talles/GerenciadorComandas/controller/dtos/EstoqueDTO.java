package com.talles.GerenciadorComandas.controller.dtos;

import com.talles.GerenciadorComandas.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {
    private Long id;
    private Produto produto;
    private int quantidade;
}
