package com.renatoviana.algafood.api.v2.modelmapper.disassembler;

import com.renatoviana.algafood.api.v2.model.request.CidadeModelRequestV2;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeModelRequestDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeModelRequestV2 cidadeModelRequest) {

        return modelMapper.map(cidadeModelRequest, Cidade.class);
    }

    public void copyToDomainObject(CidadeModelRequestV2 cidadeModelRequest, Cidade cidade) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.renatoviana.algafood.domain.model.Estado was altered from 1 to 2
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeModelRequest, cidade);
    }
}
