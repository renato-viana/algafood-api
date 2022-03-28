package com.renatoviana.algafood.api.model.dto.output;

import com.fasterxml.jackson.annotation.JsonView;
import com.renatoviana.algafood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaOutputDTO {

	@JsonView(RestauranteView.Resumo.class)
	private Long id;

	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
