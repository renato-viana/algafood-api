package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.EstadoModelResponse;
import com.renatoviana.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoModelResponse toModelResponse(Estado estado) {
        return modelMapper.map(estado, EstadoModelResponse.class);
    }

    public List<EstadoModelResponse> toCollectionModelResponse(List<Estado> estados) {
        return estados.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
