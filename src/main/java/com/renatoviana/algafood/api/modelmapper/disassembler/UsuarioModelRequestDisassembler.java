package com.renatoviana.algafood.api.modelmapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.request.UsuarioModelRequest;
import com.renatoviana.algafood.domain.model.Usuario;

@Component
public class UsuarioModelRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainObject(UsuarioModelRequest usuarioModelRequest) {
		
		return modelMapper.map(usuarioModelRequest, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioModelRequest usuarioModelRequest, Usuario usuario) {
		modelMapper.map(usuarioModelRequest, usuario);
	}
}
