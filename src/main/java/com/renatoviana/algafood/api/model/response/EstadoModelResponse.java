package com.renatoviana.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoModelResponse {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Rio de Janeiro")
	private String nome;

}
