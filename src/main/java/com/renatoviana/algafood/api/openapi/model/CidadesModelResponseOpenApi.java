package com.renatoviana.algafood.api.openapi.model;

import com.renatoviana.algafood.api.model.response.CidadeModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesModelResponse")
@Data
public class CidadesModelResponseOpenApi {

    private CidadesEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("CidadesEmbeddedModelResponse")
    @Data
    public static class CidadesEmbeddedModelResponseOpenApi {

        private List<CidadeModelResponse> cidades;
    }

}
