package com.renatoviana.algafood.api.v1.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class PedidoModelRequest {

	@Valid
	@NotNull
	private RestauranteIdModelRequest restaurante;

	@Valid
	@NotNull
	private EnderecoModelRequest enderecoEntrega;

	@Valid
	@NotNull
	private FormaPagamentoIdModelRequest formaPagamento;

	@Valid
	@Size(min = 1)
	@NotNull
	private List<ItemPedidoModelRequest> itens;

}
