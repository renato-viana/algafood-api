package com.renatoviana.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatoviana.algafood.api.assembler.GrupoOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.output.GrupoOutputDTO;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private GrupoOutputDTOAssembler grupoOutputDTOAssembler;

	@GetMapping
	public List<GrupoOutputDTO> listar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

		return grupoOutputDTOAssembler.toCollectionDTO(usuario.getGrupos());
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
	}

	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
	}
}