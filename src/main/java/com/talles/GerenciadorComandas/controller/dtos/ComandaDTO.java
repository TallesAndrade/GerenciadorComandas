package com.talles.GerenciadorComandas.controller.dtos;

import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ComandaDTO {
    private Long id;

    private String nomeCliente;

    private List<ProdutoComanda> produtosComanda = new ArrayList<>();

    private BigDecimal valorTotal;

    private LocalDateTime dataAbertura;

    private Status statusComanda;
}
