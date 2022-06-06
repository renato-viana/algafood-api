package com.renatoviana.algafood.api.v2.modelmapper.assembler;

import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v2.controller.CozinhaControllerV2;
import com.renatoviana.algafood.api.v2.model.response.CozinhaModelResponseV2;
import com.renatoviana.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelResponseAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelResponseV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    public CozinhaModelResponseAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaModelResponseV2.class);
    }

    @Override
    public CozinhaModelResponseV2 toModel(Cozinha cozinha) {
        CozinhaModelResponseV2 cozinhaModelResponse = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModelResponse);

        cozinhaModelResponse.add(resourceLinkHelper.linkToCozinhas("cozinhas"));

        return cozinhaModelResponse;
    }

    public List<CozinhaModelResponseV2> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
