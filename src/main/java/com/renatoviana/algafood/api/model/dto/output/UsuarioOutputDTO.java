package com.renatoviana.algafood.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioOutputDTO {
	
	private Long id;
	private String nome;
	private String email;
}
