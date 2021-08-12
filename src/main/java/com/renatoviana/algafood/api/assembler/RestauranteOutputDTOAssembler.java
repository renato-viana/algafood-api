package com.renatoviana.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.RestauranteOutputDTO;
import com.renatoviana.algafood.domain.model.Restaurante;

@Component
public class RestauranteOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public RestauranteOutputDTO toDTO(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteOutputDTO.class);
	}
	
	public List<RestauranteOutputDTO> toCollectionDTO(List<Restaurante> restaurantes) {
		return restaurantes.stream()
					.map(restaurante -> toDTO(restaurante))
					.collect(Collectors.toList());
	}
}
