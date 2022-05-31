package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.FormaPagamentoController;
import com.renatoviana.algafood.api.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.model.response.FormaPagamentoModelResponse;
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

    public FormaPagamentoModelResponseAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoModelResponse.class);
    }

    @Override
    public FormaPagamentoModelResponse toModel(FormaPagamento formaPagamento) {
        FormaPagamentoModelResponse formaPagamentoModelResponse =
                createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoModelResponse);

        formaPagamentoModelResponse.add(resourceLinkHelper.linkToFormasPagamento("formasPagamento"));

        return formaPagamentoModelResponse;
    }

    @Override
    public CollectionModel<FormaPagamentoModelResponse> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities)
                .add(resourceLinkHelper.linkToFormasPagamento());
    }

}
