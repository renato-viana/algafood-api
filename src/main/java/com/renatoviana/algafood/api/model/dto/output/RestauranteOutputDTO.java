package com.renatoviana.algafood.api.model.dto.output;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.renatoviana.algafood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteOutputDTO {

    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;

    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;

    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaOutputDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoOutputDTO endereco;

}
