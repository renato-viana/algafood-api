package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.response.GrupoModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuários")
@SecurityRequirement(name = "security_auth")
public interface UsuarioGrupoControllerOpenApi {

    @Operation(summary = "Lista os grupos associados a um usuário",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    CollectionModel<GrupoModelResponse> listar(
            @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId
    );

    @Operation(summary = "Desassociação de grupo com usuário",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado",
                            content = @Content(schema = @Schema(ref = "Problema")))
            })
    ResponseEntity<Void> desassociar(
            @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
            @Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId
    );

    @Operation(summary = "Associação de grupo com usuário",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado",
                            content = @Content(schema = @Schema(ref = "Problema")))
            })
    ResponseEntity<Void> associar(
            @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
            @Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId
    );

}
