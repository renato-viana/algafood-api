package com.renatoviana.algafood.api.controller.openapi;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.model.dto.input.CidadeInputDTO;
import com.renatoviana.algafood.api.model.dto.output.CidadeOutputDTO;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    public List<CidadeOutputDTO> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
    })
    CidadeOutputDTO buscar(
            @ApiParam(value = "ID de uma cidade", example = "1")
                    Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    CidadeOutputDTO adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cidade")
                    CidadeInputDTO cidadeInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
    })
    CidadeOutputDTO Atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1")
                    Long cidadeId,
            @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
                    CidadeInputDTO cidadeInput);

    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída"),
            @ApiResponse(code = 404, message = "Cidade não econtrada", response = Problem.class)
    })
    void remover(
            @ApiParam(value = "ID de uma cidade", example = "1")
                    Long cidadeId);
}
