package com.talles.GerenciadorComandas.controller.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandaRequestDTO {
    @Schema(description = "Nome do cliente",example = "Carlo Ancelotti", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String nomeCliente;

}
