package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.request.GrupoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.GrupoModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Grupos")
@SecurityRequirement(name = "security_auth")
public interface GrupoControllerOpenApi {

    @Operation(summary = "Lista os grupos")
    CollectionModel<GrupoModelResponse> listar();

    @Operation(summary = "Busca um grupo por ID",
            responses = {
                    @ApiResponse(responseCode = "400", description = "ID da grupo inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    GrupoModelResponse buscar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

    @Operation(summary = "Cadastra um grupo",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Grupo cadastrado"),
            })
    GrupoModelResponse adicionar(
            @RequestBody(description = "Representação de um novo grupo", required = true)
                    GrupoModelRequest grupoModelRequest);

    @Operation(summary = "Atualiza um grupo por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Grupo atualizado"),
                    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    GrupoModelResponse atualizar(
            @Parameter(description = "ID de um grupo", example = "1", required = true)
                    Long grupoId,

            @RequestBody(description = "Representação de um grupo com os novos dados", required = true)
                    GrupoModelRequest grupoModelRequest);

    @Operation(summary = "Exclui um grupo por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Grupo excluído"),
                    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    void remover(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

}
