package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.model.request.RestauranteModelRequest;
import com.renatoviana.algafood.api.v1.model.response.RestauranteApenasNomeModelResponse;
import com.renatoviana.algafood.api.v1.model.response.RestauranteBasicoModelResponse;
import com.renatoviana.algafood.api.v1.model.response.RestauranteModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.RestauranteApenasNomeModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.RestauranteBasicoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.RestauranteModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.RestauranteModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.renatoviana.algafood.core.security.CheckSecurity;
import com.renatoviana.algafood.domain.exception.CidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.RestauranteRepository;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteModelResponseAssembler restauranteModelResponseAssembler;

    @Autowired
    private RestauranteModelRequestDisassembler restauranteModelRequestDisassembler;

    @Autowired
    private RestauranteBasicoModelResponseAssembler restauranteBasicoModelResponseAssembler;

    @Autowired
    private RestauranteApenasNomeModelResponseAssembler restauranteApenasNomeModelResponseAssembler;

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<RestauranteBasicoModelResponse> listar() {
        return restauranteBasicoModelResponseAssembler
                .toCollectionModel(restauranteRepository.findAll());
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping(params = "projecao=apenas-nome")
    public CollectionModel<RestauranteApenasNomeModelResponse> listarApenasNomes() {
        return restauranteApenasNomeModelResponseAssembler
                .toCollectionModel(restauranteRepository.findAll());
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping("/{restauranteId}")
    public RestauranteModelResponse buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return restauranteModelResponseAssembler.toModel(restaurante);
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModelResponse adicionar(@RequestBody @Valid RestauranteModelRequest restauranteInput) {
        try {
            Restaurante restaurante = restauranteModelRequestDisassembler.toDomainObject(restauranteInput);

            restaurante = cadastroRestauranteService.salvar(restaurante);

            return restauranteModelResponseAssembler.toModel(restaurante);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/{restauranteId}")
    public RestauranteModelResponse atualizar(@PathVariable Long restauranteId,
                                              @RequestBody @Valid RestauranteModelRequest restauranteInput) {
        try {
            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);

            restauranteModelRequestDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);

            return restauranteModelResponseAssembler.toModel(restauranteAtual);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.ativar(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMuitos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.ativar(restauranteIds);

        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.inativar(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMuitos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.inativar(restauranteIds);

        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
        cadastroRestauranteService.abrir(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.fechar(restauranteId);

        return ResponseEntity.noContent().build();
    }

}
