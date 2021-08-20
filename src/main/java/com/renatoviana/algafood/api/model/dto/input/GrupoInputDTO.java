package com.renatoviana.algafood.api.model.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoInputDTO {
	
	@NotBlank
	private String nome;

}
