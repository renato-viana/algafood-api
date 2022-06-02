package com.renatoviana.algafood.api.v1.openapi.model;

import com.renatoviana.algafood.api.v1.model.response.RestauranteBasicoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantesBasicoModelResponse")
@Data
public class RestaurantesBasicoModelResponseOpenApi {

    private RestaurantesEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("RestaurantesEmbeddedModelResponse")
    @Data
    public static class RestaurantesEmbeddedModelResponseOpenApi {

        private List<RestauranteBasicoModelResponse> restaurantes;

    }

}
