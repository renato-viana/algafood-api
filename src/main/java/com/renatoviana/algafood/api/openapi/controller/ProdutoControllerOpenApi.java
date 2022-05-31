package com.renatoviana.algafood.api.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.model.request.ProdutoModelRequest;
import com.renatoviana.algafood.api.model.response.ProdutoModelResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Produtos")
public interface ProdutoControllerOpenApi {

    @ApiOperation("Lista os produtos de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    CollectionModel<ProdutoModelResponse> listar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,

            @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
                    example = "false", defaultValue = "false")
                    Boolean incluirInativos);

    @ApiOperation("Busca um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
    })
    ProdutoModelResponse buscar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,

            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId);

    @ApiOperation("Cadastra um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ProdutoModelResponse adicionar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,

            @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true)
                    ProdutoModelRequest produtoModelRequest);

    @ApiOperation("Atualiza um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
    })
    ProdutoModelResponse atualizar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,

            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId,

            @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados",
                    required = true)
                    ProdutoModelRequest produtoModelRequest);
}
