package com.renatoviana.algafood.api.assembler;

import com.renatoviana.algafood.api.model.dto.output.PedidoOutputDTO;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoOutputDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoOutputDTO  toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoOutputDTO.class);
    }

    public List<PedidoOutputDTO > toCollectionDTO(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toDTO(pedido))
                .collect(Collectors.toList());
    }
}
