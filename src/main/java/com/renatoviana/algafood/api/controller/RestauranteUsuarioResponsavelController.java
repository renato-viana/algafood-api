package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.UsuarioModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.UsuarioModelResponseAssembler;
import com.renatoviana.algafood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioModelResponseAssembler usuarioModelResponseAssembler;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Override
    @GetMapping
    public CollectionModel<UsuarioModelResponse> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<UsuarioModelResponse> usuariosModelResponse =
                usuarioModelResponseAssembler.toCollectionModel(restaurante.getResponsaveis())
                        .removeLinks()
                        .add(resourceLinkHelper.linkToResponsaveisRestaurante(restauranteId))
                        .add(resourceLinkHelper.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

        usuariosModelResponse.getContent().forEach(usuarioModelResponse -> {
            usuarioModelResponse.add(resourceLinkHelper.linkToRestauranteResponsavelDesassociacao(
                    restauranteId, usuarioModelResponse.getId(), "desassociar"));
        });

        return usuariosModelResponse;
    }

    @Override
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }
}
