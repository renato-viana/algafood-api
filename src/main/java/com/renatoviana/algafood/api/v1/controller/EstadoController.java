package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.model.request.EstadoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.EstadoModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.EstadoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.EstadoModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.renatoviana.algafood.core.security.CheckSecurity;
import com.renatoviana.algafood.domain.model.Estado;
import com.renatoviana.algafood.domain.repository.EstadoRepository;
import com.renatoviana.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoModelResponseAssembler estadoModelResponseAssembler;

    @Autowired
    private EstadoModelRequestDisassembler estadoModelRequestDisassembler;

    @CheckSecurity.Estados.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<EstadoModelResponse> listar() {
        List<Estado> estados = estadoRepository.findAll();

        return estadoModelResponseAssembler.toCollectionModel(estados);
    }

    @CheckSecurity.Estados.PodeConsultar
    @Override
    @GetMapping("/{estadoId}")
    public EstadoModelResponse buscar(@PathVariable Long estadoId) {
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

        return estadoModelResponseAssembler.toModel(estado);
    }

    @CheckSecurity.Estados.PodeEditar
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModelResponse adicionar(@RequestBody @Valid EstadoModelRequest estadoModelRequest) {
        Estado estado = estadoModelRequestDisassembler.toDomainObject(estadoModelRequest);

        estado = cadastroEstadoService.salvar(estado);

        return estadoModelResponseAssembler.toModel(estado);
    }

    @CheckSecurity.Estados.PodeEditar
    @Override
    @PutMapping("/{estadoId}")
    public EstadoModelResponse atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoModelRequest estadoModelRequest) {

        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

        estadoModelRequestDisassembler.copyToDomainObject(estadoModelRequest, estadoAtual);

        estadoAtual = cadastroEstadoService.salvar(estadoAtual);

        return estadoModelResponseAssembler.toModel(estadoAtual);
    }

    @CheckSecurity.Estados.PodeEditar
    @Override
    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }

}
