package com.renatoviana.algafood.api.openapi.model;

import com.renatoviana.algafood.api.model.response.CozinhaModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CozinhasModelResponse")
@Setter
@Getter
public class CozinhasModelResponseOpenApi {

    private CozinhaEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    private PagedModelResponseOpenApi page;

    @ApiModel("CozinhasEmbeddedModelResponse")
    @Data
    public class CozinhaEmbeddedModelResponseOpenApi {

        private List<CozinhaModelResponse> cozinhas;
    }

}
