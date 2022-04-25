package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.model.request.EstadoModelRequest;
import com.renatoviana.algafood.api.model.response.EstadoModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.EstadoModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.disassembler.EstadoModelRequestDisassembler;
import com.renatoviana.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Estado;
import com.renatoviana.algafood.domain.repository.EstadoRepository;
import com.renatoviana.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoModelResponseAssembler estadoModelResponseAssembler;

    @Autowired
    private EstadoModelRequestDisassembler estadoModelRequestDisassembler;

    @GetMapping
    public List<EstadoModelResponse> listar() {
        List<Estado> estados = estadoRepository.findAll();

        return estadoModelResponseAssembler.toCollectionModelResponse(estados);
    }

    @GetMapping("/{estadoId}")
    public EstadoModelResponse buscar(@PathVariable Long estadoId) {
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

        return estadoModelResponseAssembler.toModelResponse(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModelResponse adicionar(@RequestBody @Valid EstadoModelRequest estadoInput) {
        Estado estado = estadoModelRequestDisassembler.toDomainObject(estadoInput);

        estado = cadastroEstadoService.salvar(estado);

        return estadoModelResponseAssembler.toModelResponse(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoModelResponse atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoModelRequest estadoInput) {

        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

        estadoModelRequestDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        estadoAtual = cadastroEstadoService.salvar(estadoAtual);

        return estadoModelResponseAssembler.toModelResponse(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }
}
