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

import com.renatoviana.algafood.api.assembler.CozinhaInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.CozinhaOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.CozinhaInputDTO;
import com.renatoviana.algafood.api.model.dto.output.CozinhaOutputDTO;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.repository.CozinhaRepository;
import com.renatoviana.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaOutputDTOAssembler cozinhaOutputDTOAssembler;
	
	@Autowired
	private CozinhaInputDTODisassembler cozinhaInputDTODisassembler;

	@GetMapping
	public List<CozinhaOutputDTO> listar() {
		List<Cozinha> cozinhas = cozinhaRepository.findAll();
		
		return cozinhaOutputDTOAssembler.toCollectionDTO(cozinhas);
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaOutputDTO buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		
		return cozinhaOutputDTOAssembler.toDTO(cozinha);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaOutputDTO adicionar(@RequestBody @Valid CozinhaInputDTO cozinhaInput) {
		Cozinha cozinha = cozinhaInputDTODisassembler.toDomainObject(cozinhaInput);
		
		cozinha = cadastroCozinhaService.salvar(cozinha);
		
		return cozinhaOutputDTOAssembler.toDTO(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaOutputDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputDTO cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		cozinhaInputDTODisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		
		cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

		return cozinhaOutputDTOAssembler.toDTO(cozinhaAtual);
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinhaService.excluir(cozinhaId);
	}
}
