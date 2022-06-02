package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.v1.model.response.PermissaoModelResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {
    @ApiOperation("Lista as permissões associadas a um grupo")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    CollectionModel<PermissaoModelResponse> listar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId);

    @ApiOperation("Desassociação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada",
                    response = Problem.class)
    })
    ResponseEntity desassociar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId,

            @ApiParam(value = "ID da permissão", example = "1", required = true)
                    Long permissaoId);

    @ApiOperation("Associação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada",
                    response = Problem.class)
    })
    ResponseEntity associar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId,

            @ApiParam(value = "ID da permissão", example = "1", required = true)
                    Long permissaoId);
}
