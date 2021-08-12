package com.renatoviana.algafood.api.model.dto.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteOutputDTO {
	
	private Long id;
	private String nome;
	private BigDecimal precoFrete;
	private CozinhaOutputDTO cozinha;

}