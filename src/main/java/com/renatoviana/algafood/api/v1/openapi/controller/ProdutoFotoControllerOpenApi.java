package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.request.FotoProdutoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.FotoProdutoModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

@Tag(name = "Produtos")
@SecurityRequirement(name = "security_auth")
public interface ProdutoFotoControllerOpenApi {

    @Operation(summary = "Atualiza a foto do produto de um restaurante",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Foto do produto atualizada"),
                    @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
            })
    FotoProdutoModelResponse atualizarFoto(
            @Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
            @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId,
            @RequestBody(required = true) FotoProdutoModelRequest fotoProdutoModelRequest
    ) throws IOException;

    @Operation(summary = "Exclui a foto do produto de um restaurante",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Foto do produto excluída"),
                    @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(ref = "Problema")))
            })
    void excluir(
            @Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
            @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

    @Operation(summary = "Busca a foto do produto de um restaurante",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoModelResponse.class)),
                            @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                            @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
                    }),
                    @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(ref = "Problema")))
            })
    FotoProdutoModelResponse buscar(
            @Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
            @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId
    );

    @Operation(summary = "Busca a foto do produto de um restaurante", hidden = true)
    ResponseEntity<?> buscarFoto(Long restauranteId, Long produtoId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

}
