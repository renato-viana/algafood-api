package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @GetMapping
    public RootEntryPointModelResponse root() {
        var rootEntryPointModelResponse = new RootEntryPointModelResponse();

        rootEntryPointModelResponse.add(resourceLinkHelper.linkToCozinhas("cozinhas"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToPedidos("pedidos"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToRestaurantes("restaurantes"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToGrupos("grupos"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToUsuarios("usuarios"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToPermissoes("permissoes"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToFormasPagamento("formas-pagamento"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToEstados("estados"));
        rootEntryPointModelResponse.add(resourceLinkHelper.linkToCidades("cidades"));

        return rootEntryPointModelResponse;
    }

    private static class RootEntryPointModelResponse extends RepresentationModel<RootEntryPointModelResponse> {
    }
}
