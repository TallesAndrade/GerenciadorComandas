package com.talles.GerenciadorComandas.controller.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandaRequestDTO {
    @NotBlank
    private String nomeCliente;

}
