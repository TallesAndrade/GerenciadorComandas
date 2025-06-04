package com.talles.GerenciadorComandas.docs;

import com.talles.GerenciadorComandas.controller.dtos.*;
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

@Tag(name = "Comandas", description = "Endpoints para gerenciamento de comandas")
public interface ComandaApiDocs {

    @Operation(
            summary = "Cria uma nova comanda",
            description = "Cria uma nova comanda aberta para um cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comanda criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    ResponseEntity<ComandaResponseDTO> criarComanda(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do cliente para criar a comanda",
                    required = true
            )
            @Valid @RequestBody ComandaRequestDTO comandaDTO
    );

    @Operation(
            summary = "Adiciona um produto à comanda",
            description = "Adiciona uma quantidade específica de um produto a uma comanda aberta. Requer o ID da comanda e os dados do produto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso à comanda"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Comanda, Produto ou Estoque não encontrados"),
            @ApiResponse(responseCode = "422", description = "Quantidade solicitada excede o disponível em estoque")
    })
    ResponseEntity<ComandaResponseDTO> adicionarProduto(
            @Parameter(description = "ID da comanda que receberá o produto", example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do produto que será adicionado à comanda",
                    required = true
            )
            @Valid @RequestBody ItemComandaRequestDTO dto
    );

    @Operation(
            summary = "Atualiza o status da comanda",
            description = "Permite ajustar o status da comanda para FECHADA ou CANCELADA. Requer o ID da comanda e o novo status no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status da comanda atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status inválido fornecido"),
            @ApiResponse(responseCode = "404", description = "Comanda não encontrada")
    })
    ResponseEntity<ComandaFechadaResponseDTO> ajustarStatusComanda(
            @Parameter(description = "ID da comanda que terá o status ajustado", example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Status desejado para a comanda (FECHADA ou CANCELADA)",
                    required = true
            )
            @Valid @RequestBody StatusComandaRequestDTO dto
    );

    @Operation(
            summary = "Edita a quantidade de um produto na comanda",
            description = "Atualiza a quantidade de um produto já adicionado à comanda. Requer o ID da comanda e os dados do produto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso na comanda"),
            @ApiResponse(responseCode = "404", description = "Comanda ou Estoque não encontrados"),
            @ApiResponse(responseCode = "422", description = "Quantidade solicitada excede o disponível em estoque")
    })
    ResponseEntity<ComandaResponseDTO> editarProduto(
            @Parameter(description = "ID da comanda que terá o produto editado", example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do produto com a nova quantidade",
                    required = true
            )
            @Valid @RequestBody ItemComandaRequestDTO dto
    );

    @Operation(
            summary = "Remove um produto da comanda",
            description = "Remove completamente um produto da comanda e devolve a quantidade ao estoque."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido da comanda com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comanda ou Estoque não encontrados")
    })
    ResponseEntity<Void> deletarProduto(
            @Parameter(description = "ID da comanda", example = "1") @PathVariable Long idComanda,
            @Parameter(description = "ID do produto a ser removido", example = "5") @PathVariable Long idProduto
    );

    @Operation(
            summary = "Busca uma comanda pelo ID",
            description = "Retorna todos os dados de uma comanda com status ABERTA com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comanda encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comanda não encontrada")
    })
    ResponseEntity<ComandaResponseDTO> buscarComandaPorId(
            @Parameter(description = "ID da comanda", example = "1") @PathVariable Long id
    );

    @Operation(
            summary = "Lista todas as comandas abertas",
            description = "Retorna todas as comandas com status ABERTA registradas no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de comandas abertas retornada com sucesso")
    })
    ResponseEntity<List<ComandaResponseDTO>> listarComandas();
}
