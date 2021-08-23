package com.renatoviana.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.dto.output.FormaPagamentoOutputDTO;
import com.renatoviana.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoOutputDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamentoOutputDTO toDTO(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoOutputDTO.class);
	}
	
	public List<FormaPagamentoOutputDTO> toCollectionDTO(Collection<FormaPagamento> formaPagamentos) {
		return formaPagamentos.stream()
					.map(formaPagamento -> toDTO(formaPagamento))
					.collect(Collectors.toList());
	}
}
