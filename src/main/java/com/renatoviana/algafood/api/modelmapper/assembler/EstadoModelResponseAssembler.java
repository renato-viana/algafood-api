package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.EstadoController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.EstadoModelResponse;
import com.renatoviana.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
                .add(linkTo(EstadoController.class).withSelfRel());
    }
}
