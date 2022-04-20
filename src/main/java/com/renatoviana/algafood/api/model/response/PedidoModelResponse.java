package com.renatoviana.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoModelResponse {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private RestauranteResumoModelResponse restaurante;
    private UsuarioModelResponse cliente;
    private FormaPagamentoModelResponse formaPagamento;
    private EnderecoModelResponse enderecoEntrega;
    private List<ItemPedidoModelResponse> itens;
}
