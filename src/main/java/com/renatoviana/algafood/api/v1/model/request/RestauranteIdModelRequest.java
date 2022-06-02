package com.renatoviana.algafood.api.v1.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RestauranteIdModelRequest {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
