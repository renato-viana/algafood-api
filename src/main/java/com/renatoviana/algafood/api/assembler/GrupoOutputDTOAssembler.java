package com.renatoviana.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.GrupoOutputDTO;
import com.renatoviana.algafood.domain.model.Grupo;

@Component
public class GrupoOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public GrupoOutputDTO toDTO(Grupo grupo) {
		return modelMapper.map(grupo, GrupoOutputDTO.class);
	}
	
	public List<GrupoOutputDTO> toCollectionDTO(Collection<Grupo> grupos) {
		return grupos.stream()
					.map(grupo -> toDTO(grupo))
					.collect(Collectors.toList());
	}
}
