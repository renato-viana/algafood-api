package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.EstadoController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.EstadoModelResponse;
import com.renatoviana.algafood.core.security.Security;
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

    @Autowired
    private Security security;

    public EstadoModelResponseAssembler() {
        super(EstadoController.class, EstadoModelResponse.class);
    }

    @Override
    public EstadoModelResponse toModel(Estado estado) {
        EstadoModelResponse estadoModelResponse = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoModelResponse);

        if (security.podeConsultarEstados()) {
            estadoModelResponse.add(resourceLinkHelper.linkToEstados("estados"));
        }

        return estadoModelResponse;
    }

    @Override
    public CollectionModel<EstadoModelResponse> toCollectionModel(Iterable<? extends Estado> entities) {
        CollectionModel<EstadoModelResponse> collectionModelResponse = super.toCollectionModel(entities);

        if (security.podeConsultarEstados()) {
            collectionModelResponse.add(resourceLinkHelper.linkToEstados());
        }

        return collectionModelResponse;
    }
}
