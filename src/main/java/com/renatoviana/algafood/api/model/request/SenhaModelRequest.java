package com.renatoviana.algafood.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SenhaModelRequest {

	@NotBlank
	private String senhaAtual;

	@NotBlank
	private String novaSenha;
}