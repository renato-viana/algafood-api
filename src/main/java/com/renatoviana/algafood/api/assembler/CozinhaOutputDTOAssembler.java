package com.renatoviana.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.CozinhaOutputDTO;
import com.renatoviana.algafood.domain.model.Cozinha;

@Component
public class CozinhaOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public CozinhaOutputDTO toDTO(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaOutputDTO.class);
	}
	
	public List<CozinhaOutputDTO> toCollectionDTO(List<Cozinha> cozinhas) {
		return cozinhas.stream()
					.map(cozinha -> toDTO(cozinha))
					.collect(Collectors.toList());
	}
}
