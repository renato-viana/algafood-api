package com.renatoviana.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "grupos")
@Setter
@Getter
public class GrupoModelResponse extends RepresentationModel<GrupoModelResponse> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Gerente")
	private String nome;

}
