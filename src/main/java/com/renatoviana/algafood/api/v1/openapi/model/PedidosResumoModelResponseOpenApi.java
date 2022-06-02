package com.renatoviana.algafood.api.v1.openapi.model;

import com.renatoviana.algafood.api.v1.model.response.PedidoResumoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PedidosResumoModelResponse")
@Getter
@Setter
public class PedidosResumoModelResponseOpenApi {

    private PedidosResumoEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    private PagedModelResponseOpenApi page;

    @ApiModel("PedidosResumoEmbeddedModelResponse")
    @Data
    public static class PedidosResumoEmbeddedModelResponseOpenApi {

        private List<PedidoResumoModelResponse> pedidos;

    }
}
