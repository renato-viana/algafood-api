package com.renatoviana.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.renatoviana.algafood.api.model.dto.output.RestauranteOutputDTO;
import com.renatoviana.algafood.domain.model.Restaurante;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(Restaurante.class, RestauranteOutputDTO.class)
			.addMapping(Restaurante::getTaxaFrete, RestauranteOutputDTO::setPrecoFrete);

		return modelMapper;
	}
}
