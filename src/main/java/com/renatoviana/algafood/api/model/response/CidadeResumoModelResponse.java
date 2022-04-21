package com.renatoviana.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModelResponse {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Uberlândia")
	private String nome;

	@ApiModelProperty(example = "Minas Gerais")
	private String estado;
}
