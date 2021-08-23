package com.renatoviana.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.input.ProdutoInputDTO;
import com.renatoviana.algafood.domain.model.Produto;

@Component
public class ProdutoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Produto toDomainObject(ProdutoInputDTO produtoInput) {
		
		return modelMapper.map(produtoInput, Produto.class);
	}
	
	public void copyToDomainObject(ProdutoInputDTO produtoInput, Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
}
