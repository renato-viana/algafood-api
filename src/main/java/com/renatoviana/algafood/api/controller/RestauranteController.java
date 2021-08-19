package com.renatoviana.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatoviana.algafood.api.assembler.RestauranteInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.RestauranteOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.RestauranteInputDTO;
import com.renatoviana.algafood.api.model.dto.output.RestauranteOutputDTO;
import com.renatoviana.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.RestauranteRepository;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private RestauranteOutputDTOAssembler restauranteOutputDTOAssembler;
	
	@Autowired
	private RestauranteInputDTODisassembler restauranteInputDTODisassembler;

	@GetMapping
	public List<RestauranteOutputDTO> listar() {
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		
		return restauranteOutputDTOAssembler.toCollectionDTO(restaurantes);
	}

	@GetMapping("/{restauranteId}")
	public RestauranteOutputDTO buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		return restauranteOutputDTOAssembler.toDTO(restaurante);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteOutputDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInput);
			
			restaurante = cadastroRestauranteService.salvar(restaurante);
			
			return restauranteOutputDTOAssembler.toDTO(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteOutputDTO atualizar(@PathVariable Long restauranteId, 
			@RequestBody @Valid RestauranteInputDTO restauranteInput) {
		try {
			Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
			
			restauranteInputDTODisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
			
			restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);
			
			return restauranteOutputDTOAssembler.toDTO(restauranteAtual);
		} catch (CozinhaNaoEncontradaException e) {
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
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		cadastroRestauranteService.inativar(restauranteId);
	}
}
