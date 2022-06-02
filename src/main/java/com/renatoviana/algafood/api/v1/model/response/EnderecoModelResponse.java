package com.renatoviana.algafood.api.v1.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class EnderecoModelResponse {

	@ApiModelProperty(example = "38400-000")
	private String cep;

	@ApiModelProperty(example = "Rua Floriano Peixoto")
	private String logradouro;

	@ApiModelProperty(example = "1500")
	private String numero;

	@ApiModelProperty(example = "Apto 901")
	private String complemento;

	@ApiModelProperty(example = "Centro")
	private String bairro;

	private CidadeResumoModelResponse cidade;
}
