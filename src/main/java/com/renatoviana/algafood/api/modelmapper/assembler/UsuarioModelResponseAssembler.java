package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.UsuarioController;
import com.renatoviana.algafood.api.controller.UsuarioGrupoController;
import com.renatoviana.algafood.api.model.response.UsuarioModelResponse;
import com.renatoviana.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelResponseAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModelResponseAssembler() {
        super(UsuarioController.class, UsuarioModelResponse.class);
    }

    @Override
    public UsuarioModelResponse toModel(Usuario usuario) {
        UsuarioModelResponse usuarioModelResponse = createModelWithId(usuario.getId(), usuario);

        modelMapper.map(usuario, usuarioModelResponse);

        usuarioModelResponse.add(linkTo(UsuarioController.class).withRel("usuarios"));

        usuarioModelResponse.add(linkTo(methodOn(UsuarioGrupoController.class)
                .listar(usuario.getId())).withSelfRel());

        return usuarioModelResponse;
    }

    @Override
    public CollectionModel<UsuarioModelResponse> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(UsuarioController.class).withSelfRel());
    }
}
