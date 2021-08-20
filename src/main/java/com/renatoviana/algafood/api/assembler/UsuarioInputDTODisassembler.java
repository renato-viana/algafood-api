package com.renatoviana.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.input.UsuarioInputDTO;
import com.renatoviana.algafood.domain.model.Usuario;

@Component
public class UsuarioInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainObject(UsuarioInputDTO usuarioInput) {
		
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInputDTO usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
}
