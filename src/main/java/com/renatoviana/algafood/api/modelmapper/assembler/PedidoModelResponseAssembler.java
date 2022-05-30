package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.PedidoController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.PedidoModelResponse;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelResponseAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public PedidoModelResponseAssembler() {
        super(PedidoController.class, PedidoModelResponse.class);
    }

    @Override
    public PedidoModelResponse toModel(Pedido pedido) {
        PedidoModelResponse pedidoModelResponse = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModelResponse);

        pedidoModelResponse.add(resourceLinkHelper.linkToPedidos());

        if (pedido.podeSerConfirmado()) {
            pedidoModelResponse.add(resourceLinkHelper.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
        }

        if (pedido.podeSerEntregue()) {
            pedidoModelResponse.add(resourceLinkHelper.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
        }

        if (pedido.podeSerCancelado()) {
            pedidoModelResponse.add(resourceLinkHelper.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
        }

        pedidoModelResponse
                .getRestaurante().add(
                        resourceLinkHelper
                                .linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModelResponse
                .getCliente().add(
                        resourceLinkHelper
                                .linkToUsuario(pedido.getCliente().getId()));

        pedidoModelResponse
                .getFormaPagamento().add(
                        resourceLinkHelper
                                .linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoModelResponse
                .getEnderecoEntrega().getCidade().add(
                        resourceLinkHelper
                                .linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoModelResponse
                .getItens().forEach(item -> {
                    item.add(resourceLinkHelper
                            .linkToProduto(
                                    pedidoModelResponse
                                            .getRestaurante().getId(), item.getProdutoId(), "produto"));
                });

        return pedidoModelResponse;
    }

}
