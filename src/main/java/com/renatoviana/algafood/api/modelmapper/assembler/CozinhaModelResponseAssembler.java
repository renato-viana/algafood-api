package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.CozinhaModelResponse;
import com.renatoviana.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaModelResponse toModelResponse(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaModelResponse.class);
    }

    public List<CozinhaModelResponse> toCollectionModelResponse(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
