package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.assembler.CidadeInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.CidadeOutputDTOAssembler;
import com.renatoviana.algafood.api.controller.openapi.CidadeControllerOpenApi;
import com.renatoviana.algafood.api.model.dto.input.CidadeInputDTO;
import com.renatoviana.algafood.api.model.dto.output.CidadeOutputDTO;
import com.renatoviana.algafood.domain.exception.EstadoNaoEncontradoException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.repository.CidadeRepository;
import com.renatoviana.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeOutputDTOAssembler cidadeOutputDTOAssembler;

    @Autowired
    private CidadeInputDTODisassembler cidadeInputDTODisassembler;

    @GetMapping
    public List<CidadeOutputDTO> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();

        return cidadeOutputDTOAssembler.toCollectionDTO(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeOutputDTO buscar(
            @PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

        return cidadeOutputDTOAssembler.toDTO(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeOutputDTO adicionar(
            @RequestBody @Valid CidadeInputDTO cidadeInput) {
        try {
            Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidadeService.salvar(cidade);

            return cidadeOutputDTOAssembler.toDTO(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeOutputDTO Atualizar(
            @PathVariable Long cidadeId,
            @RequestBody @Valid CidadeInputDTO cidadeInput) {

        try {
            Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

            cidadeInputDTODisassembler.copyToDoaminObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

            return cidadeOutputDTOAssembler.toDTO(cidadeAtual);
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
