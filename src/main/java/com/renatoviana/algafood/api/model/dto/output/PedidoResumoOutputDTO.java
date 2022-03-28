package com.renatoviana.algafood.api.model.dto.output;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoOutputDTO {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private RestauranteResumoOutputDTO restaurante;
    private UsuarioOutputDTO cliente;
}
