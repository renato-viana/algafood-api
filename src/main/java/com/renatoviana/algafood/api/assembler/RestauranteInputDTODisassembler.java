package com.renatoviana.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.input.RestauranteInputDTO;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInputDTO restauranteInput) {
		
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDoaminObject(RestauranteInputDTO restauranteInput, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.renatoviana.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInput, restaurante);
	}
}
