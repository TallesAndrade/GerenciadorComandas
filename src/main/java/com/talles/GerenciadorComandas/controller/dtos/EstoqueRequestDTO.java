package com.talles.GerenciadorComandas.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueRequestDTO {

    @Schema(description = "Quantidade a ser adicionada ou subtraída do estoque. O valor deve ser no mínimo 1.", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(value = 1)
    private int quantidade;
}
