package com.renatoviana.algafood.api.assembler;

import com.renatoviana.algafood.api.model.dto.output.PedidoOutputDTO;
import com.renatoviana.algafood.api.model.dto.output.PedidoResumoOutputDTO;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoOutputDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoOutputDTO  toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoOutputDTO.class);
    }

    public List<PedidoResumoOutputDTO> toCollectionDTO(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
