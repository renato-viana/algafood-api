package com.renatoviana.algafood.api.modelmapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.request.GrupoModelRequest;
import com.renatoviana.algafood.domain.model.Grupo;

@Component
public class GrupoModelRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomainObject(GrupoModelRequest grupoModelRequest) {
		
		return modelMapper.map(grupoModelRequest, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoModelRequest grupoModelRequest, Grupo grupo) {
		modelMapper.map(grupoModelRequest, grupo);
	}
}
