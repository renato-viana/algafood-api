package com.renatoviana.algafood.api.v2.modelmapper.assembler;

import com.renatoviana.algafood.api.v2.controller.CidadeControllerV2;
import com.renatoviana.algafood.api.v2.helper.ResourceLinkHelperV2;
import com.renatoviana.algafood.api.v2.model.response.CidadeModelResponseV2;
import com.renatoviana.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CidadeModelResponseAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeModelResponseV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelperV2 resourceLinkHelper;

    public CidadeModelResponseAssemblerV2() {
        super(CidadeControllerV2.class, CidadeModelResponseV2.class);
    }

    @Override
    public CidadeModelResponseV2 toModel(Cidade cidade) {
        CidadeModelResponseV2 cidadeModelResponse = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModelResponse);

        cidadeModelResponse.add(resourceLinkHelper.linkToCidades("cidades"));

        return cidadeModelResponse;
    }

    @Override
    public CollectionModel<CidadeModelResponseV2> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper.linkToCidades());
    }
}
