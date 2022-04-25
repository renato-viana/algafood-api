package com.renatoviana.algafood.api.openapi.model;

import com.renatoviana.algafood.api.model.response.CozinhaModelResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel("RestauranteBasicoModelOpenApi")
@Setter
@Getter
public class RestauranteBasicoModelResponseOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String nome;

    @ApiModelProperty(example = "12.00")
    private BigDecimal taxaFrete;

    private CozinhaModelResponse cozinha;
}
