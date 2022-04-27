package com.renatoviana.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
public class CidadeModelResponse extends RepresentationModel<CidadeModelResponse> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Uberlândia")
    private String nome;

    private EstadoModelResponse estado;

}
