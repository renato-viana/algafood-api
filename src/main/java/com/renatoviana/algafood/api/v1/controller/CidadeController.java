package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.helper.ResourceUriHelper;
import com.renatoviana.algafood.api.v1.model.request.CidadeModelRequest;
import com.renatoviana.algafood.api.v1.model.response.CidadeModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.CidadeModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.CidadeModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.renatoviana.algafood.core.security.CheckSecurity;
import com.renatoviana.algafood.domain.exception.EstadoNaoEncontradoException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.repository.CidadeRepository;
import com.renatoviana.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeModelResponseAssembler cidadeModelResponseAssembler;

    @Autowired
    private CidadeModelRequestDisassembler cidadeModelRequestDisassembler;

    @CheckSecurity.Cidades.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<CidadeModelResponse> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();

        return cidadeModelResponseAssembler.toCollectionModel(cidades);
    }

    @CheckSecurity.Cidades.PodeConsultar
    @Override
    @GetMapping("/{cidadeId}")
    public CidadeModelResponse buscar(
            @PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

        return cidadeModelResponseAssembler.toModel(cidade);
    }

    @CheckSecurity.Cidades.PodeEditar
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelResponse adicionar(
            @RequestBody @Valid CidadeModelRequest cidadeModelRequest) {
        try {
            Cidade cidade = cidadeModelRequestDisassembler.toDomainObject(cidadeModelRequest);

            cidade = cadastroCidadeService.salvar(cidade);

            CidadeModelResponse cidadeModelResponse = cidadeModelResponseAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModelResponse.getId());

            return cidadeModelResponse;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Cidades.PodeEditar
    @Override
    @PutMapping("/{cidadeId}")
    public CidadeModelResponse atualizar(
            @PathVariable Long cidadeId,
            @RequestBody @Valid CidadeModelRequest cidadeModelRequest) {

        try {
            Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

            cidadeModelRequestDisassembler.copyToDomainObject(cidadeModelRequest, cidadeAtual);

            cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

            return cidadeModelResponseAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @CheckSecurity.Cidades.PodeEditar
    @Override
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(
            @PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }

}
