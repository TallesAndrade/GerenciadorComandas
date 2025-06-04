package com.talles.GerenciadorComandas.controller.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemComandaRequestDTO {
    @NotNull
    private Long idProduto;
    @Min(1)
    private int quantidade;
}
