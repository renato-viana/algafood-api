package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.request.RestauranteModelRequest;
import com.renatoviana.algafood.api.v1.model.response.RestauranteApenasNomeModelResponse;
import com.renatoviana.algafood.api.v1.model.response.RestauranteBasicoModelResponse;
import com.renatoviana.algafood.api.v1.model.response.RestauranteModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Restaurantes")
@SecurityRequirement(name = "security_auth")
public interface RestauranteControllerOpenApi {

    @Operation(summary = "Lista restaurantes", parameters = {
            @Parameter(name = "projeção",
                    description = "Nome da projeção",
                    example = "apenas-nome",
                    in = ParameterIn.QUERY,
                    required = false
            )
    })
    CollectionModel<RestauranteBasicoModelResponse> listar();

    @Operation(summary = "Lista restaurantes", hidden = true)
    CollectionModel<RestauranteApenasNomeModelResponse> listarApenasNomes();

    @Operation(summary = "Busca um restaurante por ID",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    RestauranteModelResponse buscar(
            @Parameter(description = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId);

    @Operation(summary = "Cadastra um restaurante",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Restaurante cadastrado"),
            })
    RestauranteModelResponse adicionar(
            @RequestBody(description = "Representação de um novo restaurante", required = true)
                    RestauranteModelRequest restauranteModelRequest);

    @Operation(summary = "Atualiza um restaurante por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    RestauranteModelResponse atualizar(
            @Parameter(description = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId,

            @RequestBody(description = "Representação de um restaurante com os novos dados",
                    required = true)
                    RestauranteModelRequest restauranteModelRequest);

    @Operation(summary = "Ativa um restaurante por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    ResponseEntity<Void> ativar(
            @Parameter(description = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId);

    @Operation(summary = "Inativa um restaurante por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    ResponseEntity<Void> inativar(
            @Parameter(description = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId);

    @Operation(summary = "Ativa múltiplos restaurantes",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")
            })
    void ativarMuitos(
            @RequestBody(description = "IDs de restaurantes", required = true)
                    List<Long> restauranteIds);

    @Operation(summary = "Inativa múltiplos restaurantes",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")
            })
    void inativarMuitos(
            @RequestBody(description = "IDs de restaurantes", required = true)
                    List<Long> restauranteIds);

    @Operation(summary = "Abre um restaurante por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    ResponseEntity<Void> abrir(
            @Parameter(description = "ID de um restaurante", example = "1", required = true)
                    Long restauranteId);

    @Operation(summary = "Fecha um restaurante por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    ResponseEntity<Void> fechar(
            @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restauranteId);

}
