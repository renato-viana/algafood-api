package com.renatoviana.algafood.api.v1.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoIdModelRequest {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;

}
