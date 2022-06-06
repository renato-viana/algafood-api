package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.model.request.GrupoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.GrupoModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.GrupoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.GrupoModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Grupo;
import com.renatoviana.algafood.domain.repository.GrupoRepository;
import com.renatoviana.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelResponseAssembler grupoModelResponseAssembler;

	@Autowired
	private GrupoModelRequestDisassembler grupoModelRequestDisassembler;

	@Override
	@GetMapping
	public CollectionModel<GrupoModelResponse> listar() {
		List<Grupo> grupos = grupoRepository.findAll();
		
		return grupoModelResponseAssembler.toCollectionModel(grupos);
	}

	@Override
	@GetMapping("/{grupoId}")
	public GrupoModelResponse buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		return grupoModelResponseAssembler.toModel(grupo);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModelResponse adicionar(@RequestBody @Valid GrupoModelRequest grupoInput) {
		 Grupo grupo = grupoModelRequestDisassembler.toDomainObject(grupoInput);
		    
		    grupo = cadastroGrupoService.salvar(grupo);
		    
		    return grupoModelResponseAssembler.toModel(grupo);
	}

	@Override
	@PutMapping("/{grupoId}")
	public GrupoModelResponse atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoModelRequest grupoInput) {

		Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);

		grupoModelRequestDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		grupoAtual = cadastroGrupoService.salvar(grupoAtual);
		
		return grupoModelResponseAssembler.toModel(grupoAtual);
	}

	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}
