package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.PermissaoModelResponse;
import com.renatoviana.algafood.core.security.Security;
import com.renatoviana.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelResponseAssembler implements RepresentationModelAssembler<Permissao, PermissaoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Autowired
    private Security security;

    @Override
    public PermissaoModelResponse toModel(Permissao permissao) {
        PermissaoModelResponse permissaoModelResponse = modelMapper.map(permissao, PermissaoModelResponse.class);
        return permissaoModelResponse;
    }

    @Override
    public CollectionModel<PermissaoModelResponse> toCollectionModel(Iterable<? extends Permissao> entities) {
        CollectionModel<PermissaoModelResponse> collectionModelResponse
                = RepresentationModelAssembler.super.toCollectionModel(entities);

        if (security.podeConsultarUsuariosGruposPermissoes()) {
            collectionModelResponse.add(resourceLinkHelper.linkToPermissoes());
        }

        return collectionModelResponse;
    }

}
