package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.model.response.PermissaoModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.PermissaoModelResponseAssembler;
import com.renatoviana.algafood.api.openapi.controller.PermissaoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Permissao;
import com.renatoviana.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoModelResponseAssembler permissaoModelResponseAssembler;

    @Override
    @GetMapping
    public CollectionModel<PermissaoModelResponse> listar() {
        List<Permissao> permissoes = permissaoRepository.findAll();

        return permissaoModelResponseAssembler.toCollectionModel(permissoes);
    }
}        
