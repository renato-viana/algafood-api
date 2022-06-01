package com.renatoviana.algafood.api.openapi.model;

import com.renatoviana.algafood.api.model.response.FormaPagamentoModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("FormasPagamentoModelResponse")
@Data
public class FormasPagamentoModelResponseOpenApi {

    private FormasPagamentoEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("FormasPagamentoEmbeddedModel")
    @Data
    public static class FormasPagamentoEmbeddedModelResponseOpenApi {

        private List<FormaPagamentoModelResponse> formasPagamento;

    }

}
