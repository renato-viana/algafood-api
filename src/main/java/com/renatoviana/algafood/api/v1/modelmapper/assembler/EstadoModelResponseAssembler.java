package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.EstadoController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.EstadoModelResponse;
import com.renatoviana.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class EstadoModelResponseAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public EstadoModelResponseAssembler() {
        super(EstadoController.class, EstadoModelResponse.class);
    }

    @Override
    public EstadoModelResponse toModel(Estado estado) {
        EstadoModelResponse estadoModelResponse = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoModelResponse);

        estadoModelResponse.add(resourceLinkHelper.linkToEstados("estados"));

        return estadoModelResponse;
    }

    @Override
    public CollectionModel<EstadoModelResponse> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper.linkToEstados());
    }
}