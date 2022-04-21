package com.renatoviana.algafood.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.renatoviana.algafood.api.model.request.RestauranteModelRequest;
import com.renatoviana.algafood.api.model.response.RestauranteModelResponse;
import com.renatoviana.algafood.api.model.view.RestauranteView;
import com.renatoviana.algafood.api.modelmapper.assembler.RestauranteModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.disassembler.RestauranteModelRequestDisassembler;
import com.renatoviana.algafood.api.openapi.model.RestauranteBasicoModelResponseOpenApi;
import com.renatoviana.algafood.domain.exception.CidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.RestauranteRepository;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Restaurantes")
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteModelResponseAssembler restauranteModelResponseAssembler;

    @Autowired
    private RestauranteModelRequestDisassembler restauranteModelRequestDisassembler;

    @ApiOperation(value = "Lista Restaurantes", response = RestauranteBasicoModelResponseOpenApi.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                    name = "projecao", paramType = "query", type = "string")
    })
    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public List<RestauranteModelResponse> listar() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        return restauranteModelResponseAssembler.toCollectionModelResponse(restaurantes);
    }

    @ApiOperation(value = "Lista Restaurantes", hidden = true)
    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public List<RestauranteModelResponse> listarApenasNome() {
        return listar();
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModelResponse buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return restauranteModelResponseAssembler.toModelResponse(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModelResponse adicionar(@RequestBody @Valid RestauranteModelRequest restauranteInput) {
        try {
            Restaurante restaurante = restauranteModelRequestDisassembler.toDomainObject(restauranteInput);

            restaurante = cadastroRestauranteService.salvar(restaurante);

            return restauranteModelResponseAssembler.toModelResponse(restaurante);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModelResponse atualizar(@PathVariable Long restauranteId,
                                              @RequestBody @Valid RestauranteModelRequest restauranteInput) {
        try {
            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);

            restauranteModelRequestDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);

            return restauranteModelResponseAssembler.toModelResponse(restauranteAtual);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroRestauranteService.excluir(id);
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.ativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMuitos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.ativar(restauranteIds);

        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.inativar(restauranteId);
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMuitos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.inativar(restauranteIds);

        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        cadastroRestauranteService.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.fechar(restauranteId);
    }
}
