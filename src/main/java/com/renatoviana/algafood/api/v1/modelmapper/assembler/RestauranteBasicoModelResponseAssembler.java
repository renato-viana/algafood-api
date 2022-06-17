package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.RestauranteController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.RestauranteBasicoModelResponse;
import com.renatoviana.algafood.core.security.Security;
import com.renatoviana.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteBasicoModelResponseAssembler extends RepresentationModelAssemblerSupport<Restaurante,
        RestauranteBasicoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Autowired
    private Security security;

    public RestauranteBasicoModelResponseAssembler() {
        super(RestauranteController.class, RestauranteBasicoModelResponse.class);
    }

    @Override
    public RestauranteBasicoModelResponse
    toModel(Restaurante restaurante) {
        RestauranteBasicoModelResponse
                restauranteModelResponse = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModelResponse);

        if (security.podeConsultarRestaurantes()) {
            restauranteModelResponse.add(resourceLinkHelper.linkToRestaurantes("restaurantes"));
        }

        if (security.podeConsultarCozinhas()) {
            restauranteModelResponse.getCozinha().add(
                    resourceLinkHelper.linkToCozinha(restaurante.getCozinha().getId()));
        }

        return restauranteModelResponse;
    }

    @Override
    public CollectionModel<RestauranteBasicoModelResponse>
    toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteBasicoModelResponse> collectionModelResponse = super.toCollectionModel(entities);

        if (security.podeConsultarRestaurantes()) {
            collectionModelResponse.add(resourceLinkHelper.linkToRestaurantes());
        }

        return collectionModelResponse;
    }

}
