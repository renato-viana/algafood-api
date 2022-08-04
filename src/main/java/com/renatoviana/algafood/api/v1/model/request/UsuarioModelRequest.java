package com.renatoviana.algafood.api.v1.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioModelRequest {

	@Schema(example = "João da Silva", required = true)
	@NotBlank
	private String nome;

	@Schema(example = "joao.ger@algafood.com.br", required = true)
	@Email
	@NotBlank
	private String email;

}
