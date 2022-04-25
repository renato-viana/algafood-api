package com.renatoviana.algafood.api.controller;

import java.util.List;

import com.renatoviana.algafood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatoviana.algafood.api.modelmapper.assembler.PermissaoModelResponseAssembler;
import com.renatoviana.algafood.api.model.response.PermissaoModelResponse;
import com.renatoviana.algafood.domain.model.Grupo;
import com.renatoviana.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoModelResponseAssembler permissaoModelResponseAssembler;
	
	@GetMapping
	public List<PermissaoModelResponse> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		return permissaoModelResponseAssembler.toCollectionModelResponse(grupo.getPermissoes());
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associarPermissao(grupoId, permissaoId);
	}
}
