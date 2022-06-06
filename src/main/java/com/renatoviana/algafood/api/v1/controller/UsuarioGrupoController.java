package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.GrupoModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.GrupoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelResponseAssembler grupoModelResponseAssembler;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Override
    @GetMapping
    public CollectionModel<GrupoModelResponse> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        CollectionModel<GrupoModelResponse> grupoModelResponse =
                grupoModelResponseAssembler.toCollectionModel(usuario.getGrupos())
                        .removeLinks()
                        .add(resourceLinkHelper.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

        grupoModelResponse.getContent().forEach(grupoModel -> {
            grupoModel.add(resourceLinkHelper.linkToUsuarioGrupoDesassociacao(
                    usuarioId, grupoModel.getId(), "desassociar"));
        });

        return grupoModelResponse;
    }

    @Override
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }
}