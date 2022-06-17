package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.ProdutoController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.FotoProdutoModelResponse;
import com.renatoviana.algafood.core.security.Security;
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

    @Autowired
    private Security security;

    public FotoProdutoModelResponseAssembler() {
        super(ProdutoController.class, FotoProdutoModelResponse.class);
    }

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Override
    public FotoProdutoModelResponse toModel(FotoProduto foto) {
        FotoProdutoModelResponse fotoProdutoModelResponse = modelMapper.map(foto, FotoProdutoModelResponse.class);

        // Quem pode consultar restaurantes, também pode consultar os produtos e fotos
        if (security.podeConsultarRestaurantes()) {
            fotoProdutoModelResponse.add(resourceLinkHelper.linkToFotoProduto(
                    foto.getRestauranteId(), foto.getProduto().getId()));

            fotoProdutoModelResponse.add(resourceLinkHelper.linkToProduto(
                    foto.getRestauranteId(), foto.getProduto().getId(), "produto"));
        }

        return fotoProdutoModelResponse;
    }

}
