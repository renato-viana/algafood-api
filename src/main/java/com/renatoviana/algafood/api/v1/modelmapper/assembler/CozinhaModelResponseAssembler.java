package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.CozinhaController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.CozinhaModelResponse;
import com.renatoviana.algafood.core.security.Security;
import com.renatoviana.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelResponseAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Autowired
    private Security security;

    public CozinhaModelResponseAssembler() {
        super(CozinhaController.class, CozinhaModelResponse.class);
    }

    @Override
    public CozinhaModelResponse toModel(Cozinha cozinha) {
        CozinhaModelResponse cozinhaModelResponse = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModelResponse);

        if (security.podeConsultarCozinhas()) {
            cozinhaModelResponse.add(resourceLinkHelper.linkToCozinhas("cozinhas"));
        }

        return cozinhaModelResponse;
    }

}
