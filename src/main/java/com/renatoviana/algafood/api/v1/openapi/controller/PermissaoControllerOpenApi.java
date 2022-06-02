package com.renatoviana.algafood.api.v1.openapi.controller;

import com.renatoviana.algafood.api.v1.model.response.PermissaoModelResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissaoModelResponse> listar();

}
