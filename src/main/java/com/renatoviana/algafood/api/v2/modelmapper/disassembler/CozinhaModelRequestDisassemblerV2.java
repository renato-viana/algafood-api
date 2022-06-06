package com.renatoviana.algafood.api.v2.modelmapper.disassembler;

import com.renatoviana.algafood.api.v2.model.request.CozinhaModelRequestV2;
import com.renatoviana.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelRequestDisassemblerV2 {

	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainObject(CozinhaModelRequestV2 cozinhaModelRequest) {

		return modelMapper.map(cozinhaModelRequest, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaModelRequestV2 cozinhaModelRequest, Cozinha cozinha) {
		modelMapper.map(cozinhaModelRequest, cozinha);
	}
}
