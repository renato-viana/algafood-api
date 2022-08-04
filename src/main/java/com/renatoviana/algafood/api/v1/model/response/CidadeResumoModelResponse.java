package com.renatoviana.algafood.api.v1.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeResumoModelResponse extends RepresentationModel<CidadeResumoModelResponse> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Uberlândia")
    private String nome;

    @Schema(example = "Minas Gerais")
    private String estado;
}
