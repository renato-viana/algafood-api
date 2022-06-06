package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.PermissaoModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.PermissaoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Grupo;
import com.renatoviana.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private PermissaoModelResponseAssembler permissaoModelResponseAssembler;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Override
    @GetMapping
    public CollectionModel<PermissaoModelResponse> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        CollectionModel<PermissaoModelResponse> permissoesModelResponse
                = permissaoModelResponseAssembler.toCollectionModel(grupo.getPermissoes())
                .removeLinks()
                .add(resourceLinkHelper.linkToGrupoPermissoes(grupoId))
                .add(resourceLinkHelper.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

        permissoesModelResponse.getContent().forEach(permissaoModel ->
                permissaoModel.add(resourceLinkHelper.linkToGrupoPermissaoDesassociacao(
                        grupoId, permissaoModel.getId(), "desassociar"))
        );

        return permissoesModelResponse;
    }

    @Override
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }
}
