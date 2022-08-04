package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.response.PermissaoModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Permissões")
@SecurityRequirement(name = "security_auth")
public interface PermissaoControllerOpenApi {

    @Operation(summary = "Lista as permissões")
    CollectionModel<PermissaoModelResponse> listar();

}
