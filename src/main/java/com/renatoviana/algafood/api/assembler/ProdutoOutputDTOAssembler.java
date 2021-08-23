package com.renatoviana.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.ProdutoOutputDTO;
import com.renatoviana.algafood.domain.model.Produto;

@Component
public class ProdutoOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public ProdutoOutputDTO toDTO(Produto produto) {
		return modelMapper.map(produto, ProdutoOutputDTO.class);
	}
	
	public List<ProdutoOutputDTO> toCollectionDTO(List<Produto> produtos) {
		return produtos.stream()
					.map(produto -> toDTO(produto))
					.collect(Collectors.toList());
	}
}
