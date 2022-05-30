package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.PedidoController;
import com.renatoviana.algafood.api.controller.RestauranteController;
import com.renatoviana.algafood.api.controller.UsuarioController;
import com.renatoviana.algafood.api.model.response.PedidoResumoModelResponse;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResumoModelResponseAssembler extends RepresentationModelAssemblerSupport<Pedido,
        PedidoResumoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoModelResponseAssembler() {
        super(PedidoController.class, PedidoResumoModelResponse.class);
    }

    @Override
    public PedidoResumoModelResponse toModel(Pedido pedido) {
        PedidoResumoModelResponse pedidoModelResponse = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModelResponse);

        pedidoModelResponse.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoModelResponse.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoModelResponse.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        return pedidoModelResponse;
    }
}
