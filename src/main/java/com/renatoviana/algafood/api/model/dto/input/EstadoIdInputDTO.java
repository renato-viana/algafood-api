package com.renatoviana.algafood.api.model.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoIdInputDTO {
	
	@NotNull
	private Long id;

}
