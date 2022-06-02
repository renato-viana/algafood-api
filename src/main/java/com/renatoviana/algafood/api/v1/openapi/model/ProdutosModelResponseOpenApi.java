package com.renatoviana.algafood.api.v1.openapi.model;

import com.renatoviana.algafood.api.v1.model.response.ProdutoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ProdutosModelResponse")
@Data
public class ProdutosModelResponseOpenApi {

    private ProdutosEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("ProdutosEmbeddedModelResponse")
    @Data
    public static class ProdutosEmbeddedModelResponseOpenApi {

        private List<ProdutoModelResponse> produtos;

    }

}
