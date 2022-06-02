package com.renatoviana.algafood.api.v1.openapi.model;

import com.renatoviana.algafood.api.v1.model.response.PermissaoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissoesModelResponse")
@Data
public class PermissoesModelResponseOpenApi {

    private PermissoesEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("PermissoesEmbeddedModelResponse")
    @Data
    public static class PermissoesEmbeddedModelResponseOpenApi {

        private List<PermissaoModelResponse> permissoes;

    }

}
