package com.renatoviana.algafood.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter 
public class FormaPagamentoIdModelRequest {

    @NotNull
    private Long id;
}
