package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.RestauranteModelResponse;
import com.renatoviana.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModelResponse toModelResponse(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteModelResponse.class);
    }

    public List<RestauranteModelResponse> toCollectionModelResponse(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
