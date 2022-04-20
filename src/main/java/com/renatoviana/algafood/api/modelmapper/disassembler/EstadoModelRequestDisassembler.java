package com.renatoviana.algafood.api.modelmapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.request.EstadoModelRequest;
import com.renatoviana.algafood.domain.model.Estado;

@Component
public class EstadoModelRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Estado toDomainObject(EstadoModelRequest estadoModelRequest) {
		
		return modelMapper.map(estadoModelRequest, Estado.class);
	}
	
	public void copyToDomainObject(EstadoModelRequest estadoModelRequest, Estado estado) {
		modelMapper.map(estadoModelRequest, estado);
	}
}
