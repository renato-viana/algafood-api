package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.request.FormaPagamentoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.FormaPagamentoModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Tag(name = "Formas de pagamento")
@SecurityRequirement(name = "security_auth")
public interface FormaPagamentoControllerOpenApi {

    @Operation(summary = "Lista as formas de pagamento")
    ResponseEntity<CollectionModel<FormaPagamentoModelResponse>> listar(@Parameter(hidden = true) ServletWebRequest request);

    @Operation(summary = "Busca uma forma de pagamento por ID",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(ref = "Problema")))
            })
    ResponseEntity<FormaPagamentoModelResponse> buscar(
            @Parameter(description = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
            @Parameter(hidden = true) ServletWebRequest request
    );

    @Operation(summary = "Cadastra uma forma de pagamento",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada"),
            })
    FormaPagamentoModelResponse adicionar(
            @RequestBody(description = "Representação de uma nova forma de pagamento", required = true)
                    FormaPagamentoModelRequest formaPagamentoModelRequest);

    @Operation(summary = "Atualiza uma forma de pagamento por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
                    @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(ref = "Problema")))
            })
    FormaPagamentoModelResponse atualizar(
            @Parameter(description = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
            @RequestBody(description = "Representação de uma forma de pagamento com os novos dados", required = true)
                    FormaPagamentoModelRequest formaPagamentoModelRequest
    );

    @Operation(summary = "Exclui uma forma de pagamento por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Forma de pagamento excluída"),
                    @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(ref = "Problema")))
            })
    void remover(@Parameter(description = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}
