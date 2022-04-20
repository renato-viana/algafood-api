package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.GrupoModelResponse;
import com.renatoviana.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModelResponse toModelResponse(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModelResponse.class);
    }

    public List<GrupoModelResponse> toCollectionModelResponse(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
