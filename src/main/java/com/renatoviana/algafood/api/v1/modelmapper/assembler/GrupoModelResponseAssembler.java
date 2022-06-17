package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.GrupoController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.GrupoModelResponse;
import com.renatoviana.algafood.core.security.Security;
import com.renatoviana.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelResponseAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Autowired
    private Security security;

    public GrupoModelResponseAssembler() {
        super(GrupoController.class, GrupoModelResponse.class);
    }

    @Override
    public GrupoModelResponse toModel(Grupo grupo) {
        GrupoModelResponse grupoModelResponse = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModelResponse);

        if (security.podeConsultarUsuariosGruposPermissoes()) {
            grupoModelResponse.add(resourceLinkHelper.linkToGrupos("grupos"));

            grupoModelResponse.add(resourceLinkHelper.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        }

        return grupoModelResponse;
    }

    @Override
    public CollectionModel<GrupoModelResponse> toCollectionModel(Iterable<? extends Grupo> entities) {
        CollectionModel<GrupoModelResponse> collectionModelResponse = super.toCollectionModel(entities);

        if (security.podeConsultarUsuariosGruposPermissoes()) {
            collectionModelResponse.add(resourceLinkHelper.linkToGrupos());
        }

        return collectionModelResponse;
    }

}
