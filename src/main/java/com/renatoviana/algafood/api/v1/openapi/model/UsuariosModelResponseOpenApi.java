package com.renatoviana.algafood.api.v1.openapi.model;

import com.renatoviana.algafood.api.v1.model.response.UsuarioModelResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UsuariosModelResponse")
@Data
public class UsuariosModelResponseOpenApi {

    private UsuariosEmbeddedModelResponseOpenApi _embedded;

    private Links _links;

    @ApiModel("UsuariosEmbeddedModelResponse")
    @Data
    public static class UsuariosEmbeddedModelResponseOpenApi {

        private List<UsuarioModelResponse> usuarios;

    }

}
