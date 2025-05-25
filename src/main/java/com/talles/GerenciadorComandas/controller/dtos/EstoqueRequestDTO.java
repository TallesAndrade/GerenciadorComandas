package com.talles.GerenciadorComandas.controller.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueRequestDTO {

    @NotNull
    @Min(value = 1)
    private int quantidade;
}
