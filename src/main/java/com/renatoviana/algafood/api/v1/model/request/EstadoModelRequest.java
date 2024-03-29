package com.renatoviana.algafood.api.v1.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class EstadoModelRequest {

	@Schema(example = "Minas Gerais", required = true)
	@NotBlank
	private String nome;

}
