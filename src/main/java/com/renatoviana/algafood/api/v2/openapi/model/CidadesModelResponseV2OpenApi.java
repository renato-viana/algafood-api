package com.renatoviana.algafood.api.v2.openapi.model;

import com.renatoviana.algafood.api.v2.model.response.CidadeModelResponseV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesModelResponse")
@Data
public class CidadesModelResponseV2OpenApi {

    private CidadesEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("CidadesEmbeddedModelResponse")
    @Data
    public static class CidadesEmbeddedModelResponseOpenApi {

        private List<CidadeModelResponseV2> cidades;

    }

}
