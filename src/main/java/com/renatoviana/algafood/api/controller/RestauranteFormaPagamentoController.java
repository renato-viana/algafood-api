package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.FormaPagamentoModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.FormaPagamentoModelResponseAssembler;
import com.renatoviana.algafood.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private FormaPagamentoModelResponseAssembler formaPagamentoModelResponseAssembler;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Override
    @GetMapping
    public CollectionModel<FormaPagamentoModelResponse> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<FormaPagamentoModelResponse> formasPagamentoModelResponse =
                formaPagamentoModelResponseAssembler.toCollectionModel(restaurante.getFormasPagamento())
                        .removeLinks()
                        .add(resourceLinkHelper.linkToRestauranteFormasPagamento(restauranteId))
                        .add(resourceLinkHelper.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));

        formasPagamentoModelResponse.getContent().forEach(
                formaPagamentoModelResponse -> formaPagamentoModelResponse.add(
                        resourceLinkHelper
                                .linkToRestauranteFormaPagamentoDesassociacao(
                                        restauranteId, formaPagamentoModelResponse.getId(), "desassociar"))
        );

        return formasPagamentoModelResponse;
    }

    @Override
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

}
