package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.v1.model.request.CozinhaModelRequest;
import com.renatoviana.algafood.api.v1.model.response.CozinhaModelResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista as cozinhas com paginação")
    PagedModel<CozinhaModelResponse> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModelResponse buscar(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                    Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada"),
    })
    CozinhaModelResponse adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
                    CozinhaModelRequest cozinhaModelRequest);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModelResponse atualizar(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                    Long cozinhaId,

            @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados", required = true)
                    CozinhaModelRequest cozinhaModelRequest);

    @ApiOperation("Exclui uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluída"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void remover(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                    Long cozinhaId);
}
