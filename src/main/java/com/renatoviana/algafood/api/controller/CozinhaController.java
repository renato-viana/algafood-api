package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.model.request.CozinhaModelRequest;
import com.renatoviana.algafood.api.model.response.CozinhaModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.CozinhaModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.disassembler.CozinhaModelRequestDisassembler;
import com.renatoviana.algafood.api.openapi.controller.CozinhaControllerOpenApi;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.repository.CozinhaRepository;
import com.renatoviana.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelResponseAssembler cozinhaModelResponseAssembler;

    @Autowired
    private CozinhaModelRequestDisassembler cozinhaModelRequestDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CozinhaModelResponse> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        PagedModel<CozinhaModelResponse> cozinhasPagedModel = pagedResourcesAssembler
                .toModel(cozinhasPage, cozinhaModelResponseAssembler);

        return cozinhasPagedModel;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModelResponse buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelResponseAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModelResponse adicionar(@RequestBody @Valid CozinhaModelRequest cozinhaInput) {
        Cozinha cozinha = cozinhaModelRequestDisassembler.toDomainObject(cozinhaInput);

        cozinha = cadastroCozinhaService.salvar(cozinha);

        return cozinhaModelResponseAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModelResponse atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaModelRequest cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        cozinhaModelRequestDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

        cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

        return cozinhaModelResponseAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
