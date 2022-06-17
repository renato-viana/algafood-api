package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.PedidoController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.PedidoResumoModelResponse;
import com.renatoviana.algafood.core.security.Security;
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

    @Autowired
    private Security security;

    public PedidoResumoModelResponseAssembler() {
        super(PedidoController.class, PedidoResumoModelResponse.class);
    }

    @Override
    public PedidoResumoModelResponse toModel(Pedido pedido) {
        PedidoResumoModelResponse pedidoModelResponse = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModelResponse);

        if (security.podePesquisarPedidos()) {
            pedidoModelResponse.add(resourceLinkHelper.linkToPedidos("pedidos"));
        }

        if (security.podeConsultarRestaurantes()) {
            pedidoModelResponse.getRestaurante().add(resourceLinkHelper.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (security.podeConsultarUsuariosGruposPermissoes()) {
            pedidoModelResponse.getCliente().add(resourceLinkHelper.linkToUsuario(pedido.getCliente().getId()));
        }

        return pedidoModelResponse;
    }
}
