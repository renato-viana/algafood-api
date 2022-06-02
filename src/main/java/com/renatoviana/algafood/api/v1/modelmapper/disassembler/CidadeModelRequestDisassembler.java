package com.renatoviana.algafood.api.v1.modelmapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.api.v1.model.request.CidadeModelRequest;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.model.Estado;

@Component
public class CidadeModelRequestDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeModelRequest cidadeModelRequest) {

        return modelMapper.map(cidadeModelRequest, Cidade.class);
    }

    public void copyToDoaminObject(CidadeModelRequest cidadeModelRequest, Cidade cidade) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.renatoviana.algafood.domain.model.Estado was altered from 1 to 2
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeModelRequest, cidade);
    }
}
