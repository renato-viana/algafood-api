package com.renatoviana.algafood.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter 
public class FormaPagamentoIdInputDTO {

    @NotNull
    private Long id;
}
