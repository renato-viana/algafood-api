package com.renatoviana.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
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

import com.renatoviana.algafood.api.assembler.EstadoInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.EstadoOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.EstadoInputDTO;
import com.renatoviana.algafood.api.model.dto.output.EstadoOutputDTO;
import com.renatoviana.algafood.domain.model.Estado;
import com.renatoviana.algafood.domain.repository.EstadoRepository;
import com.renatoviana.algafood.domain.service.CadastroEstadoService;

@Api(tags = "Estados")
@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Autowired
	private EstadoOutputDTOAssembler estadoOutputDTOAssembler;

	@Autowired
	private EstadoInputDTODisassembler estadoInputDTODisassembler;  

	@GetMapping
	public List<EstadoOutputDTO> listar() {
		List<Estado> estados = estadoRepository.findAll();
		
		return estadoOutputDTOAssembler.toCollectionDTO(estados);
	}

	@GetMapping("/{estadoId}")
	public EstadoOutputDTO buscar(@PathVariable Long estadoId) {
		Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
		
		return estadoOutputDTOAssembler.toDTO(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoOutputDTO adicionar(@RequestBody @Valid EstadoInputDTO estadoInput) {
		 Estado estado = estadoInputDTODisassembler.toDomainObject(estadoInput);
		    
		    estado = cadastroEstadoService.salvar(estado);
		    
		    return estadoOutputDTOAssembler.toDTO(estado);
	}

	@PutMapping("/{estadoId}")
	public EstadoOutputDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInputDTO estadoInput) {

		Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

		estadoInputDTODisassembler.copyToDomainObject(estadoInput, estadoAtual);
		
		estadoAtual = cadastroEstadoService.salvar(estadoAtual);
		
		return estadoOutputDTOAssembler.toDTO(estadoAtual);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstadoService.excluir(estadoId);
	}
}
