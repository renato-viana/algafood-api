package com.renatoviana.algafood.api.modelmapper.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.response.UsuarioModelResponse;
import com.renatoviana.algafood.domain.model.Usuario;

@Component
public class UsuarioModelResponseAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public UsuarioModelResponse toDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModelResponse.class);
	}
	
	public List<UsuarioModelResponse> toCollectionDTO(Collection<Usuario> usuarios) {
		return usuarios.stream()
					.map(usuario -> toDTO(usuario))
					.collect(Collectors.toList());
	}
}