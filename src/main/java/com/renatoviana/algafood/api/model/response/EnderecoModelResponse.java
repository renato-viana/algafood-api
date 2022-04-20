package com.renatoviana.algafood.api.model.response;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EnderecoModelResponse {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeResumoModelResponse cidade;
}
