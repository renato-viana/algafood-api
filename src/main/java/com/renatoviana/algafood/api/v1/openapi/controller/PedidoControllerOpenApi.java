package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.v1.model.request.PedidoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.PedidoModelResponse;
import com.renatoviana.algafood.api.v1.model.response.PedidoResumoModelResponse;
import com.renatoviana.algafood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiOperation("Pesquisa os pedidos")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    PagedModel<PedidoResumoModelResponse> pesquisar(PedidoFilter filtro, Pageable pageable);

    @ApiOperation("Registra um pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido registrado"),
    })
    PedidoModelResponse adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
                    PedidoModelRequest pedidoModelRequest);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Busca um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    PedidoModelResponse buscar(
            @ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
                    String codigoPedido);
}
