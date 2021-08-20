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

import com.renatoviana.algafood.api.assembler.GrupoInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.GrupoOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.GrupoInputDTO;
import com.renatoviana.algafood.api.model.dto.output.GrupoOutputDTO;
import com.renatoviana.algafood.domain.model.Grupo;
import com.renatoviana.algafood.domain.repository.GrupoRepository;
import com.renatoviana.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoOutputDTOAssembler grupoOutputDTOAssembler;

	@Autowired
	private GrupoInputDTODisassembler grupoInputDTODisassembler;  

	@GetMapping
	public List<GrupoOutputDTO> listar() {
		List<Grupo> grupos = grupoRepository.findAll();
		
		return grupoOutputDTOAssembler.toCollectionDTO(grupos);
	}

	@GetMapping("/{grupoId}")
	public GrupoOutputDTO buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		return grupoOutputDTOAssembler.toDTO(grupo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoOutputDTO adicionar(@RequestBody @Valid GrupoInputDTO grupoInput) {
		 Grupo grupo = grupoInputDTODisassembler.toDomainObject(grupoInput);
		    
		    grupo = cadastroGrupoService.salvar(grupo);
		    
		    return grupoOutputDTOAssembler.toDTO(grupo);
	}

	@PutMapping("/{grupoId}")
	public GrupoOutputDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInputDTO grupoInput) {

		Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);

		grupoInputDTODisassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		grupoAtual = cadastroGrupoService.salvar(grupoAtual);
		
		return grupoOutputDTOAssembler.toDTO(grupoAtual);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}
