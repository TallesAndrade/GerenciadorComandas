package com.talles.GerenciadorComandas.controller.dtos;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO{


    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal preco;
    private String codigoDeBarras;

}
