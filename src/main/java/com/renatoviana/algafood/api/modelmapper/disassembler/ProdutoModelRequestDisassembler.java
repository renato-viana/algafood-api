package com.renatoviana.algafood.api.modelmapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.request.ProdutoModelRequest;
import com.renatoviana.algafood.domain.model.Produto;

@Component
public class ProdutoModelRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Produto toDomainObject(ProdutoModelRequest produtoModelRequest) {
		
		return modelMapper.map(produtoModelRequest, Produto.class);
	}
	
	public void copyToDomainObject(ProdutoModelRequest produtoModelRequest, Produto produto) {
		modelMapper.map(produtoModelRequest, produto);
	}
}
