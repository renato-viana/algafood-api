package com.renatoviana.algafood.api.v1.openapi.model;

import com.renatoviana.algafood.api.v1.model.response.GrupoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("GruposModelResponse")
@Data
public class GruposModelResponseOpenApi {

    private GruposEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("GruposEmbeddedModelResponse")
    @Data
    public class GruposEmbeddedModelResponseOpenApi {

        private List<GrupoModelResponse> grupos;

    }

}