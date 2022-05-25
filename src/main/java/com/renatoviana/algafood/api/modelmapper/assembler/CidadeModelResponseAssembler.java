package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.controller.CidadeController;
import com.renatoviana.algafood.api.controller.EstadoController;
import com.renatoviana.algafood.api.model.response.CidadeModelResponse;
import com.renatoviana.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CidadeModelResponseAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModelResponse> {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModelResponseAssembler() {
        super(CidadeController.class, CidadeModelResponse.class);
    }

    @Override
    public CidadeModelResponse toModel(Cidade cidade) {
        CidadeModelResponse cidadeModelResponse = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModelResponse);

        cidadeModelResponse.add(linkTo(methodOn(CidadeController.class)
                .listar()).withRel("cidades"));

        cidadeModelResponse.getEstado().add(linkTo(methodOn(EstadoController.class)
                .buscar(cidadeModelResponse.getEstado().getId())).withSelfRel());

        return cidadeModelResponse;
    }

    @Override
    public CollectionModel<CidadeModelResponse> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CidadeController.class).withSelfRel());
    }
}
