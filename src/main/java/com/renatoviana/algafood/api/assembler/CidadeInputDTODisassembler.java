package com.renatoviana.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.input.CidadeInputDTO;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.model.Estado;

@Component
public class CidadeInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeInputDTO cidadeInput) {
		
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDoaminObject(CidadeInputDTO cidadeInput, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.renatoviana.algafood.domain.model.Estado was altered from 1 to 2
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
}
