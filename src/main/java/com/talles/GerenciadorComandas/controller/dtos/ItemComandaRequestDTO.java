package com.talles.GerenciadorComandas.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemComandaRequestDTO {
    @Schema(description = "ID do produto a ser modificado", example = "10")
    @NotNull
    private Long idProduto;
    @Schema(description = "Quantidade do produto", example = "2", minimum = "1")
    @Min(1)
    private int quantidade;
}
