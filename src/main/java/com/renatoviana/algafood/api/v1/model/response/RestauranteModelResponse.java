package com.renatoviana.algafood.api.v1.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteModelResponse extends RepresentationModel<RestauranteModelResponse> {

    @ApiModelProperty(example = "1")
//    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
//    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;

    @ApiModelProperty(example = "12.00")
//    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

//    @JsonView(RestauranteView.Resumo.class)
    private CozinhaModelResponse cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModelResponse endereco;

}
