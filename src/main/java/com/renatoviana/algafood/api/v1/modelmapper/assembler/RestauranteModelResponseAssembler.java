package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.RestauranteController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.RestauranteModelResponse;
import com.renatoviana.algafood.core.security.Security;
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

    @Autowired
    private Security security;

    public RestauranteModelResponseAssembler() {
        super(RestauranteController.class, RestauranteModelResponse.class);
    }

    @Override
    public RestauranteModelResponse toModel(Restaurante restaurante) {
        RestauranteModelResponse restauranteModelResponse = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModelResponse);

        if (security.podeConsultarRestaurantes()) {
            restauranteModelResponse.add(resourceLinkHelper.linkToRestaurantes("restaurantes"));
        }

        if (security.podeGerenciarCadastroRestaurantes()) {
            if (restaurante.ativacaoPermitida()) {
                restauranteModelResponse.add(
                        resourceLinkHelper.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
            }

            if (restaurante.inativacaoPermitida()) {
                restauranteModelResponse.add(
                        resourceLinkHelper.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
            }
        }

        if (security.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
            if (restaurante.aberturaPermitida()) {
                restauranteModelResponse.add(
                        resourceLinkHelper.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
            }

            if (restaurante.fechamentoPermitido()) {
                restauranteModelResponse.add(
                        resourceLinkHelper.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
            }
        }

        if (security.podeConsultarRestaurantes()) {
            restauranteModelResponse.add(resourceLinkHelper.linkToProdutos(restaurante.getId(), "produtos"));
        }

        if (security.podeConsultarCozinhas()) {
            restauranteModelResponse.getCozinha().add(
                    resourceLinkHelper.linkToCozinha(restaurante.getCozinha().getId()));
        }

        if (security.podeConsultarCidades()) {
            if (restauranteModelResponse.getEndereco() != null
                    && restauranteModelResponse.getEndereco().getCidade() != null) {
                restauranteModelResponse.getEndereco().getCidade().add(
                        resourceLinkHelper.linkToCidade(restaurante.getEndereco().getCidade().getId()));
            }
        }

        if (security.podeConsultarRestaurantes()) {
            restauranteModelResponse.add(resourceLinkHelper.linkToRestauranteFormasPagamento(restaurante.getId(),
                    "formas-pagamento"));
        }

        if (security.podeGerenciarCadastroRestaurantes()) {
            restauranteModelResponse.add(resourceLinkHelper.linkToRestauranteResponsaveis(restaurante.getId(),
                    "responsaveis"));
        }

        return restauranteModelResponse;
    }

    @Override
    public CollectionModel<RestauranteModelResponse> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteModelResponse> collectionModelResponse = super.toCollectionModel(entities);

        if (security.podeConsultarRestaurantes()) {
            collectionModelResponse.add(resourceLinkHelper.linkToRestaurantes());
        }

        return collectionModelResponse;
    }

}
