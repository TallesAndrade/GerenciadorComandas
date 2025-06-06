package com.talles.GerenciadorComandas.controller.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Nome do produto.", example = "Fanta 2L", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String nome;

    @Schema(description = "Preço do produto. Deve ser um valor positivo.", example = "9.50", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal preco;

    @Schema(description = "Código de barras do produto (opcional).", example = "7894900011517")
    private String codigoDeBarras;

}
