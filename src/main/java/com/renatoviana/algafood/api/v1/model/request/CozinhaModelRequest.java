package com.renatoviana.algafood.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModelRequest {

	@ApiModelProperty(example = "Brasileira", required = true)
	@NotBlank
	private String nome;

}
