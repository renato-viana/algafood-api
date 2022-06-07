package com.renatoviana.algafood.api.v2.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.v2.model.request.CidadeModelRequestV2;
import com.renatoviana.algafood.api.v2.model.response.CidadeModelResponseV2;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
public interface CidadeControllerV2OpenApi {
    @ApiOperation("Lista as cidades")
    CollectionModel<CidadeModelResponseV2> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    CidadeModelResponseV2 buscar(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada"),
    })
    CidadeModelResponseV2 adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
                    CidadeModelRequestV2 cidadeModelRequest);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    CidadeModelResponseV2 atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId,

            @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados", required = true)
                    CidadeModelRequestV2 cidadeModelRequest);

    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    void remover(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId);

}
