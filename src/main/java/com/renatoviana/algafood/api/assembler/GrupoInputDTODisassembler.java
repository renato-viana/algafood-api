package com.renatoviana.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.input.GrupoInputDTO;
import com.renatoviana.algafood.domain.model.Grupo;

@Component
public class GrupoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomainObject(GrupoInputDTO grupoInput) {
		
		return modelMapper.map(grupoInput, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoInputDTO grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
	}
}
