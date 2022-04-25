package com.renatoviana.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoModelResponse {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "CONSULTAR_COZINHAS")
    private String nome;

    @ApiModelProperty(example = "Permite consultar cozinhas")
    private String descricao;

}
