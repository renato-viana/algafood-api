package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.ProdutoController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.FotoProdutoModelResponse;
import com.renatoviana.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelResponseAssembler
        extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModelResponseAssembler() {
        super(ProdutoController.class, FotoProdutoModelResponse.class);
    }

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Override
    public FotoProdutoModelResponse toModel(FotoProduto foto) {
        FotoProdutoModelResponse fotoProdutoModelResponse = modelMapper.map(foto, FotoProdutoModelResponse.class);

        fotoProdutoModelResponse.add(resourceLinkHelper.linkToFotoProduto(
                foto.getRestauranteId(), foto.getProduto().getId()));

        fotoProdutoModelResponse.add(resourceLinkHelper.linkToProduto(
                foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

        return fotoProdutoModelResponse;
    }

}
