package com.renatoviana.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoModelResponse {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
