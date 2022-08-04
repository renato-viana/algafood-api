package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.request.CidadeModelRequest;
import com.renatoviana.algafood.api.v1.model.response.CidadeModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Cidades")
@SecurityRequirement(name = "security_auth")
public interface CidadeControllerOpenApi {

    @Operation(summary = "Lista as cidades")
    CollectionModel<CidadeModelResponse> listar();

    @Operation(summary = "Busca uma cidade por ID",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    CidadeModelResponse buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

    @Operation(summary = "Cadastra uma cidade",
            responses = {@ApiResponse(responseCode = "201", description = "Cidade cadastrada")}
    )
    CidadeModelResponse adicionar(
            @RequestBody(description = "Representação de uma nova cidade", required = true)
                    CidadeModelRequest cidadeModelRequest);

    @Operation(summary = "Atualiza uma cidade por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
                    @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    CidadeModelResponse atualizar(
            @Parameter(description = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId,
            @RequestBody(description = "Representação de uma cidade com os novos dados", required = true)
                    CidadeModelRequest cidadeModelRequest);

    @Operation(summary = "Exclui uma cidade por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cidade excluída"),
                    @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    void remover(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

}
