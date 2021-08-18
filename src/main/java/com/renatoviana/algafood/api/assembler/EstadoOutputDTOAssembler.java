package com.renatoviana.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.EstadoOutputDTO;
import com.renatoviana.algafood.domain.model.Estado;

@Component
public class EstadoOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public EstadoOutputDTO toDTO(Estado estado) {
		return modelMapper.map(estado, EstadoOutputDTO.class);
	}
	
	public List<EstadoOutputDTO> toCollectionDTO(List<Estado> estados) {
		return estados.stream()
					.map(estado -> toDTO(estado))
					.collect(Collectors.toList());
	}
}
