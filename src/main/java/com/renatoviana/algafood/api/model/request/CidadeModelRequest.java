package com.renatoviana.algafood.api.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModelRequest {

	@ApiModelProperty(example = "Petrópolis", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdModelRequest estado;

}
