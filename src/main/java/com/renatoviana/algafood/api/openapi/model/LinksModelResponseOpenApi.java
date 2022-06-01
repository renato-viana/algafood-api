package com.renatoviana.algafood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Links")
public class LinksModelResponseOpenApi {

    private LinkModelResponse rel;

    @Setter
    @Getter
    @ApiModel("Link")
    private static class LinkModelResponse {
        private String href;
        private boolean templated;
    }
}
