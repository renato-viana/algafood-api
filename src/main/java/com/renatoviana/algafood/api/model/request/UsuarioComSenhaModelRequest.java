package com.renatoviana.algafood.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioComSenhaModelRequest extends UsuarioModelRequest {

	@NotBlank
	private String senha;
}
