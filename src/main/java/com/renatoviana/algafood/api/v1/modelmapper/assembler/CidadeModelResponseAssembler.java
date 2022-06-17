package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.CidadeController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.CidadeModelResponse;
import com.renatoviana.algafood.core.security.Security;
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

    @Autowired
    private Security security;

    public CidadeModelResponseAssembler() {
        super(CidadeController.class, CidadeModelResponse.class);
    }

    @Override
    public CidadeModelResponse toModel(Cidade cidade) {
        CidadeModelResponse cidadeModelResponse = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModelResponse);

        if (security.podeConsultarCidades()) {
            cidadeModelResponse.add(resourceLinkHelper.linkToCidades("cidades"));
        }

        if (security.podeConsultarEstados()) {
            cidadeModelResponse.getEstado().add(resourceLinkHelper.linkToEstado(cidadeModelResponse.getEstado().getId()));
        }

        return cidadeModelResponse;
    }

    @Override
    public CollectionModel<CidadeModelResponse> toCollectionModel(Iterable<? extends Cidade> entities) {
        CollectionModel<CidadeModelResponse> collectionModelResponse = super.toCollectionModel(entities);

        if (security.podeConsultarCidades()) {
            collectionModelResponse.add(resourceLinkHelper.linkToCidades());
        }

        return collectionModelResponse;
    }

}
