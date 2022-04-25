package com.renatoviana.algafood.api.openapi.controller;

import com.renatoviana.algafood.api.exceptionhandler.Problem;
import com.renatoviana.algafood.api.model.request.SenhaModelRequest;
import com.renatoviana.algafood.api.model.request.UsuarioComSenhaModelRequest;
import com.renatoviana.algafood.api.model.request.UsuarioModelRequest;
import com.renatoviana.algafood.api.model.response.UsuarioModelResponse;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    List<UsuarioModelResponse> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioModelResponse buscar(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
    UsuarioModelResponse adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
                    UsuarioComSenhaModelRequest usuarioComSenhaModelRequest);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioModelResponse atualizar(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId,

            @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados",
                    required = true)
                    UsuarioModelRequest usuarioModelRequest);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void alterarSenha(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
                    Long usuarioId,

            @ApiParam(name = "corpo", value = "Representação de uma nova senha",
                    required = true)
                    SenhaModelRequest senha);
}
