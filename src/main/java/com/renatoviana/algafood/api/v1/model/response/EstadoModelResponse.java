package com.renatoviana.algafood.api.v1.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "estados")
@Setter
@Getter
public class EstadoModelResponse extends RepresentationModel<EstadoModelResponse> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Minas Gerais")
    private String nome;

}
