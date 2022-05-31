package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.model.response.GrupoModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.GrupoModelResponseAssembler;
import com.renatoviana.algafood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelResponseAssembler grupoModelResponseAssembler;

    @Override
    @GetMapping
    public CollectionModel<GrupoModelResponse> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        return grupoModelResponseAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks();
    }

    @Override
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
    }

    @Override
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
    }
}