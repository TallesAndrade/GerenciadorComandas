package com.talles.GerenciadorComandas.controller.dtos;

import com.talles.GerenciadorComandas.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record StatusComandaRequestDTO(

        @Schema(
                description = "Novo status da comanda (FECHADA ou CANCELADA)",
                example = "FECHADA"
        )
        @NotNull
        Status status
) {}

