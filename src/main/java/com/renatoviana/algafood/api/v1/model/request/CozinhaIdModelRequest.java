package com.renatoviana.algafood.api.v1.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaIdModelRequest {
	
	@NotNull
	private Long id;

}
