package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.UsuarioController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.UsuarioModelResponse;
import com.renatoviana.algafood.core.security.Security;
import com.renatoviana.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelResponseAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Autowired
    private Security security;

    public UsuarioModelResponseAssembler() {
        super(UsuarioController.class, UsuarioModelResponse.class);
    }

    @Override
    public UsuarioModelResponse toModel(Usuario usuario) {
        UsuarioModelResponse usuarioModelResponse = createModelWithId(usuario.getId(), usuario);

        modelMapper.map(usuario, usuarioModelResponse);

        if (security.podeConsultarUsuariosGruposPermissoes()) {
            usuarioModelResponse.add(resourceLinkHelper.linkToUsuarios("usuarios"));

            usuarioModelResponse.add(resourceLinkHelper.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
        }

        return usuarioModelResponse;
    }

    @Override
    public CollectionModel<UsuarioModelResponse> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper.linkToUsuarios());
    }
}
