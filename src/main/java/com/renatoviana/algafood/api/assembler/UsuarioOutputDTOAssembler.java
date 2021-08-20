package com.renatoviana.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.UsuarioOutputDTO;
import com.renatoviana.algafood.domain.model.Usuario;

@Component
public class UsuarioOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public UsuarioOutputDTO toDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioOutputDTO.class);
	}
	
	public List<UsuarioOutputDTO> toCollectionDTO(List<Usuario> usuarios) {
		return usuarios.stream()
					.map(usuario -> toDTO(usuario))
					.collect(Collectors.toList());
	}
}
