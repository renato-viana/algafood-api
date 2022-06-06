package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.v1.model.request.FormaPagamentoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.FormaPagamentoModelResponse;
import com.renatoviana.algafood.api.v1.openapi.model.FormasPagamentoModelResponseOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation(value = "Lista as formas de pagamento", response = FormasPagamentoModelResponseOpenApi.class)
    ResponseEntity<CollectionModel<FormaPagamentoModelResponse>> listar(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    ResponseEntity<FormaPagamentoModelResponse> buscar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId,

            ServletWebRequest request);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada"),
    })
    FormaPagamentoModelResponse adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true)
                    FormaPagamentoModelRequest formaPagamentoModelRequest);

    @ApiOperation("Atualiza uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    FormaPagamentoModelResponse atualizar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId,

            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados", required = true)
                    FormaPagamentoModelRequest formaPagamentoModelRequest);

    @ApiOperation("Exclui uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluída"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void remover(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
                    Long formaPagamentoId);
}