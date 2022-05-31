package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.PedidoController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.PedidoResumoModelResponse;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoModelResponseAssembler extends RepresentationModelAssemblerSupport<Pedido,
        PedidoResumoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public PedidoResumoModelResponseAssembler() {
        super(PedidoController.class, PedidoResumoModelResponse.class);
    }

    @Override
    public PedidoResumoModelResponse toModel(Pedido pedido) {
        PedidoResumoModelResponse pedidoModelResponse = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModelResponse);

        pedidoModelResponse.add(resourceLinkHelper.linkToPedidos("pedidos"));

        pedidoModelResponse.getRestaurante().add(resourceLinkHelper.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModelResponse.getCliente().add(resourceLinkHelper.linkToUsuario(pedido.getCliente().getId()));

        return pedidoModelResponse;
    }
}
