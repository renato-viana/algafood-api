package com.renatoviana.algafood.api.openapi.model;

import com.renatoviana.algafood.api.model.response.ProdutoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ProdutosModel")
@Data
public class ProdutosModelResponseOpenApi {

    private ProdutosEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("ProdutosEmbeddedModel")
    @Data
    public static class ProdutosEmbeddedModelResponseOpenApi {

        private List<ProdutoModelResponse> produtos;

    }

}
