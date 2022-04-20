package com.renatoviana.algafood.api.modelmapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.model.request.FormaPagamentoModelRequest;
import com.renatoviana.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelRequestDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamento toDomainObject(FormaPagamentoModelRequest formaPagamentoModelRequest) {

		return modelMapper.map(formaPagamentoModelRequest, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoModelRequest formaPagamentoModelRequest, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoModelRequest, formaPagamento);
	}
}
