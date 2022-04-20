package com.renatoviana.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
public class CidadeModelResponse {

	@ApiModelProperty(example = "1")
    private Long id;

	@ApiModelProperty(example = "Petrópolis")
    private String nome;
    private EstadoModelResponse estado;

}
