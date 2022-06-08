package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.model.request.CozinhaModelRequest;
import com.renatoviana.algafood.api.v1.model.response.CozinhaModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.CozinhaModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.CozinhaModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.repository.CozinhaRepository;
import com.renatoviana.algafood.domain.service.CadastroCozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

//    private static final Logger logger = LoggerFactory.getLogger(CozinhaController.class);

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

    @Override
    @GetMapping
    public PagedModel<CozinhaModelResponse> listar(@PageableDefault(size = 10) Pageable pageable) {
        log.info("Consultando cozinhas com páginas de {} registros...", pageable.getPageSize());

        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        PagedModel<CozinhaModelResponse> cozinhasPagedModel = pagedResourcesAssembler
                .toModel(cozinhasPage, cozinhaModelResponseAssembler);

        return cozinhasPagedModel;
    }

    @Override
    @GetMapping("/{cozinhaId}")
    public CozinhaModelResponse buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelResponseAssembler.toModel(cozinha);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModelResponse adicionar(@RequestBody @Valid CozinhaModelRequest cozinhaModelRequest) {
        Cozinha cozinha = cozinhaModelRequestDisassembler.toDomainObject(cozinhaModelRequest);

        cozinha = cadastroCozinhaService.salvar(cozinha);

        return cozinhaModelResponseAssembler.toModel(cozinha);
    }

    @Override
    @PutMapping("/{cozinhaId}")
    public CozinhaModelResponse atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaModelRequest cozinhaModelRequest) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        cozinhaModelRequestDisassembler.copyToDomainObject(cozinhaModelRequest, cozinhaAtual);

        cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

        return cozinhaModelResponseAssembler.toModel(cozinhaAtual);
    }

    @Override
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
