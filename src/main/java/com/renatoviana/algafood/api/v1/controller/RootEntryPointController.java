package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.core.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Autowired
    private Security security;

    @GetMapping
    public RootEntryPointModelResponse root() {
        var rootEntryPointModelResponse = new RootEntryPointModelResponse();

        if (security.podeConsultarCozinhas()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToCozinhas("cozinhas"));
        }

        if (security.podePesquisarPedidos()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToPedidos("pedidos"));
        }

        if (security.podeConsultarRestaurantes()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToRestaurantes("restaurantes"));
        }

        if (security.podeConsultarUsuariosGruposPermissoes()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToGrupos("grupos"));
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToUsuarios("usuarios"));
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToPermissoes("permissoes"));
        }

        if (security.podeConsultarFormasPagamento()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToFormasPagamento("formas-pagamento"));
        }

        if (security.podeConsultarEstados()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToEstados("estados"));
        }

        if (security.podeConsultarCidades()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToCidades("cidades"));
        }

        if (security.podeConsultarEstatisticas()) {
            rootEntryPointModelResponse.add(resourceLinkHelper.linkToEstatisticas("estatisticas"));
        }

        return rootEntryPointModelResponse;
    }

    private static class RootEntryPointModelResponse extends RepresentationModel<RootEntryPointModelResponse> {
    }
}
