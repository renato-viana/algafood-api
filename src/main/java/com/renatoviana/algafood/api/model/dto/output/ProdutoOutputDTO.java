package com.renatoviana.algafood.api.model.dto.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoOutputDTO {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;

}
