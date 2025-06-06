package com.talles.GerenciadorComandas.docs;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.ProdutoResponseDTO;
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

@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos e seu cadastro.")
public interface ProdutoApiDocs {

    @Operation(
            summary = "Cria um novo produto",
            description = "Cadastra um novo produto no sistema e cria seu respectivo controle de estoque com saldo inicial zero."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o produto")
    })
    ResponseEntity<ProdutoResponseDTO> criarProduto(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do produto a ser criado",
                    required = true
            )
            @Valid @RequestBody ProdutoRequestDTO produtoDTO
    );

    @Operation(
            summary = "Lista todos os produtos",
            description = "Retorna a lista completa de todos os produtos cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    })
    ResponseEntity<List<ProdutoResponseDTO>> listarProdutos();

    @Operation(
            summary = "Busca um produto pelo ID",
            description = "Retorna os detalhes de um produto específico com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ResponseEntity<ProdutoResponseDTO> procurarPorId(
            @Parameter(description = "ID do produto a ser buscado", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Deleta um produto",
            description = "Remove um produto do sistema com base no ID. Esta operação não remove o registro de estoque associado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ResponseEntity<Void> deletarPorId(
            @Parameter(description = "ID do produto a ser deletado", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualiza um produto",
            description = "Atualiza os dados de um produto existente com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @Parameter(description = "ID do produto a ser atualizado", example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados para o produto. Campos não fornecidos não serão alterados.",
                    required = true
            )
            @RequestBody ProdutoRequestDTO produtoDTO
    );
}