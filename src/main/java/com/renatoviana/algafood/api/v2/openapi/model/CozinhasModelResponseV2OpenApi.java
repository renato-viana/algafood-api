package com.renatoviana.algafood.api.v2.openapi.model;

import com.renatoviana.algafood.api.v2.model.response.CidadeModelResponseV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CozinhasModelResponse")
@Setter
@Getter
public class CozinhasModelResponseV2OpenApi {

    private CozinhasModelResponseV2OpenApi _embedded;

    private Links _links;

    private PageModelResponseV2OpenApi page;

    @ApiModel("CozinhasEmbeddedModelResponse")
    @Data
    public static class CozinhasEmbeddedModelResponseOpenApi {

        private List<CidadeModelResponseV2> cozinhas;

    }

}
