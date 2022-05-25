package com.renatoviana.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "estados")
@Setter
@Getter
public class EstadoModelResponse extends RepresentationModel<EstadoModelResponse> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Rio de Janeiro")
	private String nome;

}
