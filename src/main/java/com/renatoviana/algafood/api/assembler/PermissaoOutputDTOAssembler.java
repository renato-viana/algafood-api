package com.renatoviana.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.PermissaoOutputDTO;
import com.renatoviana.algafood.domain.model.Permissao;

@Component
public class PermissaoOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public PermissaoOutputDTO toDTO(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoOutputDTO.class);
	}
	
	public List<PermissaoOutputDTO> toCollectionDTO(Collection<Permissao> permissoes) {
		return permissoes.stream()
					.map(permissao -> toDTO(permissao))
					.collect(Collectors.toList());
	}
}
