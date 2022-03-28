package com.renatoviana.algafood.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class PedidoInputDTO {

	@Valid
	@NotNull
	private RestauranteIdInputDTO restaurante;

	@Valid
	@NotNull
	private EnderecoInputDTO enderecoEntrega;

	@Valid
	@NotNull
	private FormaPagamentoIdInputDTO formaPagamento;

	@Valid
	@Size(min = 1)
	@NotNull
	private List<ItemPedidoInputDTO> itens;

}
