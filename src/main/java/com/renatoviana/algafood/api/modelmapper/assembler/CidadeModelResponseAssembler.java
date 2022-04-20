package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.CidadeModelResponse;
import com.renatoviana.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModelResponse toModelResponse(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModelResponse.class);
    }

    public List<CidadeModelResponse> toCollectionModelResponse(List<Cidade> cidades) {
        return cidades.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
