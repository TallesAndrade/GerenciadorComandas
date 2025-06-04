package com.talles.GerenciadorComandas.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemComandaRequestDTO {
    @Schema(description = "ID do produto a ser modificado", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Long idProduto;
    @Schema(description = "Quantidade do produto", example = "2", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(1)
    private int quantidade;
}
