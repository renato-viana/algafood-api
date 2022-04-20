package com.renatoviana.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.renatoviana.algafood.api.openapi.controller.GrupoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatoviana.algafood.api.modelmapper.disassembler.GrupoModelRequestDisassembler;
import com.renatoviana.algafood.api.modelmapper.assembler.GrupoModelResponseAssembler;
import com.renatoviana.algafood.api.model.request.GrupoModelRequest;
import com.renatoviana.algafood.api.model.response.GrupoModelResponse;
import com.renatoviana.algafood.domain.model.Grupo;
import com.renatoviana.algafood.domain.repository.GrupoRepository;
import com.renatoviana.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelResponseAssembler grupoModelResponseAssembler;

	@Autowired
	private GrupoModelRequestDisassembler grupoModelRequestDisassembler;

	@GetMapping
	public List<GrupoModelResponse> listar() {
		List<Grupo> grupos = grupoRepository.findAll();
		
		return grupoModelResponseAssembler.toCollectionModelResponse(grupos);
	}

	@GetMapping("/{grupoId}")
	public GrupoModelResponse buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		return grupoModelResponseAssembler.toModelResponse(grupo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModelResponse adicionar(@RequestBody @Valid GrupoModelRequest grupoInput) {
		 Grupo grupo = grupoModelRequestDisassembler.toDomainObject(grupoInput);
		    
		    grupo = cadastroGrupoService.salvar(grupo);
		    
		    return grupoModelResponseAssembler.toModelResponse(grupo);
	}

	@PutMapping("/{grupoId}")
	public GrupoModelResponse atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoModelRequest grupoInput) {

		Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);

		grupoModelRequestDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		grupoAtual = cadastroGrupoService.salvar(grupoAtual);
		
		return grupoModelResponseAssembler.toModelResponse(grupoAtual);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}
