package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.request.SenhaModelRequest;
import com.renatoviana.algafood.api.v1.model.request.UsuarioComSenhaModelRequest;
import com.renatoviana.algafood.api.v1.model.request.UsuarioModelRequest;
import com.renatoviana.algafood.api.v1.model.response.UsuarioModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Usuários")
@SecurityRequirement(name = "security_auth")
public interface UsuarioControllerOpenApi {

    @Operation(summary = "Lista os usuários")
    CollectionModel<UsuarioModelResponse> listar();

    @Operation(summary = "Busca um usuário por ID",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    UsuarioModelResponse buscar(
            @Parameter(description = "ID do usuário", example = "1", required = true)
                    Long usuarioId);

    @Operation(summary = "Cadastra um usuário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
            })
    UsuarioModelResponse adicionar(
            @RequestBody(description = "Representação de um novo usuário", required = true)
                    UsuarioComSenhaModelRequest usuarioComSenhaModelRequest);

    @Operation(summary = "Atualiza um usuário por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    UsuarioModelResponse atualizar(
            @Parameter(description = "ID do usuário", example = "1", required = true)
                    Long usuarioId,

            @RequestBody(description = "Representação de um usuário com os novos dados",
                    required = true)
                    UsuarioModelRequest usuarioModelRequest);

    @Operation(summary = "Atualiza a senha de um usuário",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    void alterarSenha(
            @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
            @RequestBody(description = "Representação de uma nova senha", required = true) SenhaModelRequest senha
    );

}
