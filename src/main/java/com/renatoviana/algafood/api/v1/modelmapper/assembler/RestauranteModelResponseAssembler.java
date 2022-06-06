package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.RestauranteController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.RestauranteModelResponse;
import com.renatoviana.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelResponseAssembler
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public RestauranteModelResponseAssembler() {
        super(RestauranteController.class, RestauranteModelResponse.class);
    }

    @Override
    public RestauranteModelResponse toModel(Restaurante restaurante) {
        RestauranteModelResponse restauranteModelResponse = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModelResponse);

        restauranteModelResponse.add(resourceLinkHelper.linkToRestaurantes("restaurantes"));

        if (restaurante.ativacaoPermitida()) {
            restauranteModelResponse.add(
                    resourceLinkHelper.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteModelResponse.add(
                    resourceLinkHelper.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteModelResponse.add(
                    resourceLinkHelper.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteModelResponse.add(
                    resourceLinkHelper.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }

        restauranteModelResponse.add(resourceLinkHelper.linkToProdutos(restaurante.getId(), "produtos"));

        restauranteModelResponse.getCozinha().add(
                resourceLinkHelper.linkToCozinha(restaurante.getCozinha().getId()));

        if (restauranteModelResponse.getEndereco() != null
                && restauranteModelResponse.getEndereco().getCidade() != null) {
            restauranteModelResponse.getEndereco().getCidade().add(
                    resourceLinkHelper.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }

        restauranteModelResponse.add(resourceLinkHelper.linkToRestauranteFormasPagamento(restaurante.getId(),
                "formas-pagamento"));

        restauranteModelResponse.add(resourceLinkHelper.linkToRestauranteResponsaveis(restaurante.getId(),
                "responsaveis"));

        return restauranteModelResponse;
    }

    @Override
    public CollectionModel<RestauranteModelResponse>
    toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper
                        .linkToRestaurantes());
    }

}
