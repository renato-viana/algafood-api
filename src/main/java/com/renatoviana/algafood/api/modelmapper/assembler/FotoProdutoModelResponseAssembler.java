package com.renatoviana.algafood.api.modelmapper.assembler;

import com.renatoviana.algafood.api.model.response.FotoProdutoModelResponse;
import com.renatoviana.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModelResponse toModelResponse(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoModelResponse.class);
    }

}
