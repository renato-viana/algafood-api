package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.v1.model.request.CidadeModelRequest;
import com.renatoviana.algafood.api.v1.model.response.CidadeModelResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    CollectionModel<CidadeModelResponse> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
    })
    CidadeModelResponse buscar(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    CidadeModelResponse adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
                    CidadeModelRequest cidadeModelRequest);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
    })
    CidadeModelResponse Atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId,
            @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados", required = true)
                    CidadeModelRequest cidadeModelRequest);

    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída"),
            @ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
    })
    void remover(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long cidadeId);
}