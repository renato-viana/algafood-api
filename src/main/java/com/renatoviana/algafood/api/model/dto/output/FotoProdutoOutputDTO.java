package com.renatoviana.algafood.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoOutputDTO {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
