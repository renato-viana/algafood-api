package com.renatoviana.algafood.api.model.dto.output;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EnderecoOutputDTO {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeResumoOutputDTO cidade;
}
