package com.renatoviana.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModelResponse {
	
	private Long id;
	private String nome;
	private String email;
}
