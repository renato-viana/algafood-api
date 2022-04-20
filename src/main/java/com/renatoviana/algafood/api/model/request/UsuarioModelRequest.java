package com.renatoviana.algafood.api.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModelRequest {
	
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
}
