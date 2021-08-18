package com.renatoviana.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.CidadeOutputDTO;
import com.renatoviana.algafood.domain.model.Cidade;

@Component
public class CidadeOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public CidadeOutputDTO toDTO(Cidade cidade) {
		return modelMapper.map(cidade, CidadeOutputDTO.class);
	}
	
	public List<CidadeOutputDTO> toCollectionDTO(List<Cidade> cidades) {
		return cidades.stream()
					.map(cidade -> toDTO(cidade))
					.collect(Collectors.toList());
	}
}
