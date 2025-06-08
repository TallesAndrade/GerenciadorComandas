package com.talles.GerenciadorComandas.docs;

import com.talles.GerenciadorComandas.controller.dtos.EstoqueRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.EstoqueResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Estoque", description = "Endpoints para gerenciamento do estoque de produtos.")
public interface EstoqueApiDocs {

    @Operation(
            summary = "Adiciona saldo ao estoque",
            description = "Incrementa a quantidade de um produto no estoque."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque do produto não encontrado"),
            @ApiResponse(responseCode = "422", description = "Produto está inativo no sistema")
    })
    ResponseEntity<EstoqueResponseDTO> adicionarSaldoEstoque(
            @Parameter(description = "ID do estoque (geralmente igual ao ID do produto)", example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Quantidade a ser adicionada",
                    required = true
            )
            @Valid @RequestBody EstoqueRequestDTO estoqueDTO
    );

    @Operation(
            summary = "Busca estoque por ID",
            description = "Retorna a quantidade em estoque de um produto específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque do produto não encontrado")
    })
    ResponseEntity<EstoqueResponseDTO> buscarPorProdutoId(
            @Parameter(description = "ID do estoque (geralmente igual ao ID do produto)", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Lista todo o estoque",
            description = "Retorna a lista completa de todos os registros de estoque."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estoque retornada com sucesso")
    })
    ResponseEntity<List<EstoqueResponseDTO>> buscarTodos();

    @Operation(
            summary = "Deleta um registro de estoque",
            description = "Remove um registro de estoque. Só é possível se a quantidade em estoque for zero."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro de estoque deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "422", description = "Não é possível excluir: Ainda há unidades disponíveis.")
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "ID do estoque a ser deletado", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Subtrai saldo do estoque",
            description = "Decrementa a quantidade de um produto no estoque."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo subtraído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "422", description = "Quantidade a ser removida é maior que o saldo disponível")
    })
    ResponseEntity<EstoqueResponseDTO> removerSaldo(
            @Parameter(description = "ID do estoque a ser ajustado", example = "1")
            @PathVariable long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Quantidade a ser subtraída",
                    required = true
            )
            @Valid @RequestBody EstoqueRequestDTO estoqueDTO
    );
}