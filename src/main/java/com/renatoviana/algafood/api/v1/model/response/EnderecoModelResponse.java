package com.renatoviana.algafood.api.v1.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class EnderecoModelResponse {

    @Schema(example = "38400-000")
    private String cep;

    @Schema(example = "Rua Floriano Peixoto")
    private String logradouro;

    @Schema(example = "1500")
    private String numero;

    @Schema(example = "Apto 901")
    private String complemento;

    @Schema(example = "Centro")
    private String bairro;

    private CidadeResumoModelResponse cidade;
}
