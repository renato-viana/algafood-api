package com.renatoviana.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.renatoviana.algafood.api.model.dto.output.EnderecoOutputDTO;
import com.renatoviana.algafood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		var enderecoToEnderecoOutputDTOTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoOutputDTO.class);
		
		enderecoToEnderecoOutputDTOTypeMap.<String>addMapping(
				src -> src.getCidade().getEstado().getNome(),
				(dest, value) -> dest.getCidade().setEstado(value));
		
		return modelMapper;
	}
}
