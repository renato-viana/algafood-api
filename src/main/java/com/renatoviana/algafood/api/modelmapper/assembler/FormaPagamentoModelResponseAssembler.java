package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.FormaPagamentoModelResponse;
import com.renatoviana.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModelResponse toModelResponse(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModelResponse.class);
    }

    public List<FormaPagamentoModelResponse> toCollectionModelResponse(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
