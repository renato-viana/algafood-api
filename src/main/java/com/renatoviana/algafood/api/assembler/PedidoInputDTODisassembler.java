package com.renatoviana.algafood.api.assembler;

import com.renatoviana.algafood.api.model.dto.input.PedidoInputDTO;
import com.renatoviana.algafood.domain.model.Pedido;
import com.renatoviana.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Pedido toDomainObject(PedidoInputDTO pedidoInput) {
		
		return modelMapper.map(pedidoInput, Pedido.class);
	}
	
	public void copyToDoaminObject(PedidoInputDTO pedidoInput, Pedido pedido) {
		modelMapper.map(pedidoInput, pedido);
	}
}
