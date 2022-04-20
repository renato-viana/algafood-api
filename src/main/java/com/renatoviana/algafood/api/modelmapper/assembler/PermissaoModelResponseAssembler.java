package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.PermissaoModelResponse;
import com.renatoviana.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoModelResponse toModelResponse(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModelResponse.class);
    }

    public List<PermissaoModelResponse> toCollectionModelResponse(Collection<Permissao> permissoes) {
        return permissoes.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
