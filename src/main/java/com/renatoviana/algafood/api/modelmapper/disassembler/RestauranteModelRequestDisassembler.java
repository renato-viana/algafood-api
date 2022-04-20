package com.renatoviana.algafood.api.modelmapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.request.RestauranteModelRequest;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteModelRequest restauranteModelRequest) {
		
		return modelMapper.map(restauranteModelRequest, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteModelRequest restauranteModelRequest, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.renatoviana.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		if(restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		modelMapper.map(restauranteModelRequest, restaurante);
	}
}
