package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.PedidoModelResponse;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModelResponse toModelResponse(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModelResponse.class);
    }

    public List<PedidoModelResponse> toCollectionModelResponse(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
