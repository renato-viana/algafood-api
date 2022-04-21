package com.renatoviana.algafood.api.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeIdModelRequest {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;

}
