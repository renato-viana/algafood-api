package com.renatoviana.algafood.api.v1.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.controller.CozinhaController;
import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.response.CozinhaModelResponse;
import com.renatoviana.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CozinhaModelResponseAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public CozinhaModelResponseAssembler() {
        super(CozinhaController.class, CozinhaModelResponse.class);
    }

    @Override
    public CozinhaModelResponse toModel(Cozinha cozinha) {
        CozinhaModelResponse cozinhaModelResponse = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModelResponse);

        cozinhaModelResponse.add(resourceLinkHelper.linkToCozinhas("cozinhas"));

        return cozinhaModelResponse;
    }

    public List<CozinhaModelResponse> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}