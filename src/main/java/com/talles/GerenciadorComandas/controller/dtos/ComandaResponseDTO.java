package com.talles.GerenciadorComandas.controller.dtos;

import com.talles.GerenciadorComandas.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandaResponseDTO {
    private Long id;
    private String nomeCliente;
    private List<ProdutoComandaResponseDTO> produtosComanda = new ArrayList<>();
    private BigDecimal valorTotal;
    private String dataAbertura;
    private Status statusComanda;
}
