package com.renatoviana.algafood.api.v2.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaModelResponseV2 extends RepresentationModel<CozinhaModelResponseV2> {

	@ApiModelProperty(example = "1")
	private Long idCozinha;

	@ApiModelProperty(example = "Brasileira")
	private String nomeCozinha;

}
