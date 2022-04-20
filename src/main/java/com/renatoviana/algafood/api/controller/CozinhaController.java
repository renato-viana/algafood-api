package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.modelmapper.disassembler.CozinhaModelRequestDisassembler;
import com.renatoviana.algafood.api.modelmapper.assembler.CozinhaModelResponseAssembler;
import com.renatoviana.algafood.api.model.request.CozinhaModelRequest;
import com.renatoviana.algafood.api.model.response.CozinhaModelResponse;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.repository.CozinhaRepository;
import com.renatoviana.algafood.domain.service.CadastroCozinhaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cozinhas")
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelResponseAssembler cozinhaModelResponseAssembler;

    @Autowired
    private CozinhaModelRequestDisassembler cozinhaModelRequestDisassembler;

    @GetMapping
    public Page<CozinhaModelResponse> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        List<CozinhaModelResponse> cozinhasOutput = cozinhaModelResponseAssembler.toCollectionModelResponse(cozinhasPage.getContent());

        Page<CozinhaModelResponse> cozinhasOutputPage = new PageImpl<>(cozinhasOutput, pageable, cozinhasPage.getTotalElements());

        return cozinhasOutputPage;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModelResponse buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelResponseAssembler.toModelResponse(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModelResponse adicionar(@RequestBody @Valid CozinhaModelRequest cozinhaInput) {
        Cozinha cozinha = cozinhaModelRequestDisassembler.toDomainObject(cozinhaInput);

        cozinha = cadastroCozinhaService.salvar(cozinha);

        return cozinhaModelResponseAssembler.toModelResponse(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModelResponse atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaModelRequest cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        cozinhaModelRequestDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

        cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

        return cozinhaModelResponseAssembler.toModelResponse(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
