package com.renatoviana.algafood.api.v2.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CozinhaModelRequestV2 {

	@ApiModelProperty(example = "Brasileira", required = true)
	@NotBlank
	private String nomeCozinha;

}
