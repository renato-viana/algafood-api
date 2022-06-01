package com.renatoviana.algafood.api.openapi.model;

import com.renatoviana.algafood.api.model.response.EstadoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("EstadosModelResponse")
@Data
public class EstadosModelResponseOpenApi {

    private EstadosEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("EstadosEmbeddedModel")
    @Data
    public static class EstadosEmbeddedModelResponseOpenApi {

        private List<EstadoModelResponse> estados;

    }

}
