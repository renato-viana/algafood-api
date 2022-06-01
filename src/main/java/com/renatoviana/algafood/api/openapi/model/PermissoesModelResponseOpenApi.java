package com.renatoviana.algafood.api.openapi.model;

import com.renatoviana.algafood.api.model.response.PermissaoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissoesModel")
@Data
public class PermissoesModelResponseOpenApi {

    private PermissoesEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("PermissoesEmbeddedModel")
    @Data
    public class PermissoesEmbeddedModelResponseOpenApi {

        private List<PermissaoModelResponse> permissoes;

    }

}
