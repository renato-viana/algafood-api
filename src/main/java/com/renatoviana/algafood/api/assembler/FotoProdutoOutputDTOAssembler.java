package com.renatoviana.algafood.api.assembler;

import com.renatoviana.algafood.api.model.dto.output.FotoProdutoOutputDTO;
import com.renatoviana.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoOutputDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoOutputDTO toDTO(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoOutputDTO.class);
    }

}
