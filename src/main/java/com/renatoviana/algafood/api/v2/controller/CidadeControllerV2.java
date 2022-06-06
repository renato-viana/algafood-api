package com.renatoviana.algafood.api.v2.controller;

import com.renatoviana.algafood.api.helper.ResourceUriHelper;
import com.renatoviana.algafood.api.v2.model.request.CidadeModelRequestV2;
import com.renatoviana.algafood.api.v2.model.response.CidadeModelResponseV2;
import com.renatoviana.algafood.api.v2.modelmapper.assembler.CidadeModelResponseAssemblerV2;
import com.renatoviana.algafood.api.v2.modelmapper.disassembler.CidadeModelRequestDisassemblerV2;
import com.renatoviana.algafood.core.web.AlgaMediaTypes;
import com.renatoviana.algafood.domain.exception.EstadoNaoEncontradoException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.repository.CidadeRepository;
import com.renatoviana.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeModelResponseAssemblerV2 cidadeModelResponseAssembler;

    @Autowired
    private CidadeModelRequestDisassemblerV2 cidadeModelRequestDisassembler;

    @GetMapping
    public CollectionModel<CidadeModelResponseV2> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();

        return cidadeModelResponseAssembler.toCollectionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeModelResponseV2 buscar(
            @PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

        return cidadeModelResponseAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelResponseV2 adicionar(
            @RequestBody @Valid CidadeModelRequestV2 cidadeModelRequest) {
        try {
            Cidade cidade = cidadeModelRequestDisassembler.toDomainObject(cidadeModelRequest);

            cidade = cadastroCidadeService.salvar(cidade);

            CidadeModelResponseV2 cidadeModelResponse = cidadeModelResponseAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModelResponse.getIdCidade());

            return cidadeModelResponse;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModelResponseV2 Atualizar(
            @PathVariable Long cidadeId,
            @RequestBody @Valid CidadeModelRequestV2 cidadeModelRequest) {

        try {
            Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

            cidadeModelRequestDisassembler.copyToDomainObject(cidadeModelRequest, cidadeAtual);

            cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

            return cidadeModelResponseAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(
            @PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }
}
