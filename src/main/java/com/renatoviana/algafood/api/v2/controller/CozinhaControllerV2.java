package com.renatoviana.algafood.api.v2.controller;

import com.renatoviana.algafood.api.v2.model.request.CozinhaModelRequestV2;
import com.renatoviana.algafood.api.v2.model.response.CozinhaModelResponseV2;
import com.renatoviana.algafood.api.v2.modelmapper.assembler.CozinhaModelResponseAssemblerV2;
import com.renatoviana.algafood.api.v2.modelmapper.disassembler.CozinhaModelRequestDisassemblerV2;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.repository.CozinhaRepository;
import com.renatoviana.algafood.domain.service.CadastroCozinhaService;
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

@RestController
@RequestMapping(path = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelResponseAssemblerV2 cozinhaModelResponseAssembler;

    @Autowired
    private CozinhaModelRequestDisassemblerV2 cozinhaModelRequestDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CozinhaModelResponseV2> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        PagedModel<CozinhaModelResponseV2> cozinhasPagedModel = pagedResourcesAssembler
                .toModel(cozinhasPage, cozinhaModelResponseAssembler);

        return cozinhasPagedModel;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModelResponseV2 buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelResponseAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModelResponseV2 adicionar(@RequestBody @Valid CozinhaModelRequestV2 cozinhaModelRequest) {
        Cozinha cozinha = cozinhaModelRequestDisassembler.toDomainObject(cozinhaModelRequest);

        cozinha = cadastroCozinhaService.salvar(cozinha);

        return cozinhaModelResponseAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModelResponseV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaModelRequestV2 cozinhaModelRequest) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        cozinhaModelRequestDisassembler.copyToDomainObject(cozinhaModelRequest, cozinhaAtual);

        cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

        return cozinhaModelResponseAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
