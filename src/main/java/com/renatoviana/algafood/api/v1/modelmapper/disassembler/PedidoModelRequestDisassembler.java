package com.renatoviana.algafood.api.v1.modelmapper.disassembler;

import com.renatoviana.algafood.api.v1.model.request.PedidoModelRequest;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Pedido toDomainObject(PedidoModelRequest pedidoModelRequest) {
		
		return modelMapper.map(pedidoModelRequest, Pedido.class);
	}
	
	public void copyToDoaminObject(PedidoModelRequest pedidoModelRequest, Pedido pedido) {
		modelMapper.map(pedidoModelRequest, pedido);
	}
}
