package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.RestauranteController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.RestauranteBasicoModelResponse;
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

    public RestauranteBasicoModelResponseAssembler() {
        super(RestauranteController.class, RestauranteBasicoModelResponse.class);
    }

    @Override
    public RestauranteBasicoModelResponse
    toModel(Restaurante restaurante) {
        RestauranteBasicoModelResponse
                restauranteModelResponse = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModelResponse);

        restauranteModelResponse
                .add(resourceLinkHelper
                        .linkToRestaurantes("restaurantes"));

        restauranteModelResponse
                .getCozinha().add(
                        resourceLinkHelper
                                .linkToCozinha(restaurante.getCozinha().getId()));

        return restauranteModelResponse;
    }

    @Override
    public CollectionModel<RestauranteBasicoModelResponse>
    toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper
                        .linkToRestaurantes());
    }

}
