package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.PedidoController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.PedidoModelResponse;
import com.renatoviana.algafood.core.security.Security;
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

    @Autowired
    private Security security;

    public PedidoModelResponseAssembler() {
        super(PedidoController.class, PedidoModelResponse.class);
    }

    @Override
    public PedidoModelResponse toModel(Pedido pedido) {
        PedidoModelResponse pedidoModelResponse = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModelResponse);

        if (security.podePesquisarPedidos()) {
            pedidoModelResponse.add(resourceLinkHelper.linkToPedidos("pedidos"));
        }

        if (security.podeGerenciarPedidos(pedido.getCodigo())) {
            if (pedido.podeSerConfirmado()) {
                pedidoModelResponse.add(resourceLinkHelper.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
            }

            if (pedido.podeSerEntregue()) {
                pedidoModelResponse.add(resourceLinkHelper.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
            }

            if (pedido.podeSerCancelado()) {
                pedidoModelResponse.add(resourceLinkHelper.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
            }
        }

        if (security.podeConsultarRestaurantes()) {
            pedidoModelResponse.getRestaurante().add(
                            resourceLinkHelper.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (security.podeConsultarUsuariosGruposPermissoes()) {
            pedidoModelResponse
                    .getCliente().add(
                            resourceLinkHelper.linkToUsuario(pedido.getCliente().getId()));
        }

        if (security.podeConsultarFormasPagamento()) {
            pedidoModelResponse
                    .getFormaPagamento().add(
                            resourceLinkHelper.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        }

        if (security.podeConsultarCidades()) {
            pedidoModelResponse
                    .getEnderecoEntrega().getCidade().add(
                            resourceLinkHelper.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
        }

        // Quem pode consultar restaurantes, também pode consultar os produtos dos restaurantes
        if (security.podeConsultarRestaurantes()) {
            pedidoModelResponse
                    .getItens().forEach(item -> {
                        item.add(resourceLinkHelper
                                .linkToProduto(
                                        pedidoModelResponse
                                                .getRestaurante().getId(), item.getProdutoId(), "produto"));
                    });
        }
        
        return pedidoModelResponse;
    }

}
