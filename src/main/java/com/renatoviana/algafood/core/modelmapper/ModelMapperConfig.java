package com.renatoviana.algafood.core.modelmapper;

import com.renatoviana.algafood.api.v1.model.request.ItemPedidoModelRequest;
import com.renatoviana.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.renatoviana.algafood.api.v1.model.response.EnderecoModelResponse;
import com.renatoviana.algafood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(ItemPedidoModelRequest.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		var enderecoToEnderecoOutputDTOTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModelResponse.class);
		
		enderecoToEnderecoOutputDTOTypeMap.<String>addMapping(
				src -> src.getCidade().getEstado().getNome(),
				(dest, value) -> dest.getCidade().setEstado(value));

		return modelMapper;
	}
}
