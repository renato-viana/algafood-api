package com.renatoviana.algafood.api.v1.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeModelRequest {

    @Schema(example = "Uberlândia", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdModelRequest estado;

}
