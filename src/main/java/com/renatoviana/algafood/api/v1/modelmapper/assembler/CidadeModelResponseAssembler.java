package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.CidadeController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.CidadeModelResponse;
import com.renatoviana.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CidadeModelResponseAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public CidadeModelResponseAssembler() {
        super(CidadeController.class, CidadeModelResponse.class);
    }

    @Override
    public CidadeModelResponse toModel(Cidade cidade) {
        CidadeModelResponse cidadeModelResponse = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModelResponse);

        cidadeModelResponse.add(resourceLinkHelper.linkToCidades("cidades"));

        cidadeModelResponse.getEstado().add(resourceLinkHelper.linkToEstado(cidadeModelResponse.getEstado().getId()));

        return cidadeModelResponse;
    }

    @Override
    public CollectionModel<CidadeModelResponse> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper.linkToCidades());
    }
}