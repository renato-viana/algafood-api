package com.renatoviana.algafood.api.v2.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModelResponseV2 extends RepresentationModel<CidadeModelResponseV2> {

    @ApiModelProperty(example = "1")
    private Long idCidade;

    @ApiModelProperty(example = "Uberlândia")
    private String nomeCidade;

    private Long idEstado;
    private String nomeEstado;

}