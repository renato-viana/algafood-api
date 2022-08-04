package com.renatoviana.algafood.api.v1.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissoes")
@Setter
@Getter
public class PermissaoModelResponse extends RepresentationModel<PermissaoModelResponse> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "CONSULTAR_COZINHAS")
    private String nome;

    @Schema(example = "Permite consultar cozinhas")
    private String descricao;

}
