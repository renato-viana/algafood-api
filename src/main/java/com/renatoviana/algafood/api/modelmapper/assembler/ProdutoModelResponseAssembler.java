package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.ProdutoController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.ProdutoModelResponse;
import com.renatoviana.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelResponseAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public ProdutoModelResponseAssembler() {
        super(ProdutoController.class, ProdutoModelResponse.class);
    }

    @Override
    public ProdutoModelResponse toModel(Produto produto) {
        ProdutoModelResponse produtoModel = createModelWithId(
                produto.getId(), produto, produto.getRestaurante().getId());

        modelMapper.map(produto, produtoModel);

        produtoModel.add(resourceLinkHelper.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

        return produtoModel;
    }

}
