package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.FormaPagamentoController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.FormaPagamentoModelResponse;
import com.renatoviana.algafood.core.security.Security;
import com.renatoviana.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoModelResponseAssembler
        extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Autowired
    private Security security;

    public FormaPagamentoModelResponseAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoModelResponse.class);
    }

    @Override
    public FormaPagamentoModelResponse toModel(FormaPagamento formaPagamento) {
        FormaPagamentoModelResponse formaPagamentoModelResponse =
                createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoModelResponse);

        if (security.podeConsultarFormasPagamento()) {
            formaPagamentoModelResponse.add(resourceLinkHelper.linkToFormasPagamento("formasPagamento"));
        }

        return formaPagamentoModelResponse;
    }

    @Override
    public CollectionModel<FormaPagamentoModelResponse> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        CollectionModel<FormaPagamentoModelResponse> collectionModelResponse = super.toCollectionModel(entities);

        if (security.podeConsultarFormasPagamento()) {
            collectionModelResponse.add(resourceLinkHelper.linkToFormasPagamento());
        }

        return collectionModelResponse;
    }

}
