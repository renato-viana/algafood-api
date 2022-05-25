package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.CozinhaController;
import com.renatoviana.algafood.api.model.response.CozinhaModelResponse;
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

    public CozinhaModelResponseAssembler() {
        super(CozinhaController.class, CozinhaModelResponse.class);
    }

    @Override
    public CozinhaModelResponse toModel(Cozinha cozinha) {
        CozinhaModelResponse cozinhaModelResponse = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModelResponse);

        cozinhaModelResponse.add(linkTo(CozinhaController.class).withRel("cozinhas"));

        return cozinhaModelResponse;
    }

    public List<CozinhaModelResponse> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
