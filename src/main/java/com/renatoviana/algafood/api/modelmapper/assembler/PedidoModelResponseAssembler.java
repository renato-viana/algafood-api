package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.*;
import com.renatoviana.algafood.api.model.response.PedidoModelResponse;
import com.renatoviana.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoModelResponseAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModelResponseAssembler() {
        super(PedidoController.class, PedidoModelResponse.class);
    }

    @Override
    public PedidoModelResponse toModel(Pedido pedido) {
        PedidoModelResponse pedidoModelResponse = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModelResponse);

        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        TemplateVariables pageVariables = new TemplateVariables(
                new TemplateVariable("page", VariableType.REQUEST_PARAM),
                new TemplateVariable("size", VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", VariableType.REQUEST_PARAM)
        );

        TemplateVariables filtroVariables = new TemplateVariables(
                new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM)
        );

        pedidoModelResponse.add(Link.of(UriTemplate.of(pedidosUrl,
                pageVariables.concat(filtroVariables)), "pedidos"));

        pedidoModelResponse.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoModelResponse.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        pedidoModelResponse.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

        pedidoModelResponse.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoModelResponse.getItens().forEach(item -> {
            item.add(linkTo(methodOn(ProdutoController.class)
                    .buscar(pedidoModelResponse.getRestaurante().getId(), item.getProdutoId()))
                    .withRel("produto"));
        });

        return pedidoModelResponse;
    }

}
