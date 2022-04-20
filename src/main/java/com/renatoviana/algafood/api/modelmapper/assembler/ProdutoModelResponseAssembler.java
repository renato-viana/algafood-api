package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.ProdutoModelResponse;
import com.renatoviana.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModelResponse toModelResponse(Produto produto) {
        return modelMapper.map(produto, ProdutoModelResponse.class);
    }

    public List<ProdutoModelResponse> toCollectionModelResponse(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
