package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.ResourceUriHelper;
import com.renatoviana.algafood.api.model.request.CidadeModelRequest;
import com.renatoviana.algafood.api.model.response.CidadeModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.CidadeModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.disassembler.CidadeModelRequestDisassembler;
import com.renatoviana.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.renatoviana.algafood.domain.exception.EstadoNaoEncontradoException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.repository.CidadeRepository;
import com.renatoviana.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeModelResponseAssembler cidadeModelResponseAssembler;

    @Autowired
    private CidadeModelRequestDisassembler cidadeModelRequestDisassembler;

    @Override
    @GetMapping
    public List<CidadeModelResponse> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();

        return cidadeModelResponseAssembler.toCollectionModelResponse(cidades);
    }

    @Override
    @GetMapping("/{cidadeId}")
    public CidadeModelResponse buscar(
            @PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

        return cidadeModelResponseAssembler.toModelResponse(cidade);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelResponse adicionar(
            @RequestBody @Valid CidadeModelRequest cidadeInput) {
        try {
            Cidade cidade = cidadeModelRequestDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidadeService.salvar(cidade);

            CidadeModelResponse cidadeModelResponse = cidadeModelResponseAssembler.toModelResponse(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModelResponse.getId());

            return cidadeModelResponse;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    @PutMapping("/{cidadeId}")
    public CidadeModelResponse Atualizar(
            @PathVariable Long cidadeId,
            @RequestBody @Valid CidadeModelRequest cidadeInput) {

        try {
            Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

            cidadeModelRequestDisassembler.copyToDoaminObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

            return cidadeModelResponseAssembler.toModelResponse(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @Override
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(
            @PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }
}
