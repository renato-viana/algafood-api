package com.renatoviana.algafood.api.modelmapper.disassembler;

import com.renatoviana.algafood.api.model.request.CozinhaModelRequest;
import com.renatoviana.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelRequestDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainObject(CozinhaModelRequest cozinhaModelRequest) {

		return modelMapper.map(cozinhaModelRequest, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaModelRequest cozinhaModelRequest, Cozinha cozinha) {
		modelMapper.map(cozinhaModelRequest, cozinha);
	}
}
