package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.RestauranteController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.RestauranteApenasNomeModelResponse;
import com.renatoviana.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteApenasNomeModelResponseAssembler extends RepresentationModelAssemblerSupport<Restaurante,
        RestauranteApenasNomeModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public RestauranteApenasNomeModelResponseAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeModelResponse.class);
    }

    @Override
    public RestauranteApenasNomeModelResponse toModel(Restaurante restaurante) {
        RestauranteApenasNomeModelResponse restauranteModelResponse = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModelResponse);

        restauranteModelResponse
                .add(resourceLinkHelper
                        .linkToRestaurantes("restaurantes"));

        return restauranteModelResponse;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeModelResponse>
    toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper.
                        linkToRestaurantes());
    }

}
