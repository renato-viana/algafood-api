package com.renatoviana.algafood.api.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class EstadoModelRequest {

	@ApiModelProperty(example = "Minas Gerais", required = true)
	@NotBlank
	private String nome;

}
