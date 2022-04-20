package com.renatoviana.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatoviana.algafood.api.assembler.UsuarioInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.UsuarioOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.SenhaInputDTO;
import com.renatoviana.algafood.api.model.dto.input.UsuarioComSenhaInputDTO;
import com.renatoviana.algafood.api.model.dto.input.UsuarioInputDTO;
import com.renatoviana.algafood.api.model.dto.output.UsuarioOutputDTO;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.repository.UsuarioRepository;
import com.renatoviana.algafood.domain.service.CadastroUsuarioService;

@Api(tags = "Usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private UsuarioOutputDTOAssembler usuarioOutputDTOAssembler;

	@Autowired
	private UsuarioInputDTODisassembler usuarioInputDTODisassembler;  

	@GetMapping
	public List<UsuarioOutputDTO> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return usuarioOutputDTOAssembler.toCollectionDTO(usuarios);
	}

	@GetMapping("/{usuarioId}")
	public UsuarioOutputDTO buscar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		
		return usuarioOutputDTOAssembler.toDTO(usuario);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioOutputDTO adicionar(@RequestBody @Valid UsuarioComSenhaInputDTO usuarioInput) {
		 Usuario usuario = usuarioInputDTODisassembler.toDomainObject(usuarioInput);
		    
		    usuario = cadastroUsuarioService.salvar(usuario);
		    
		    return usuarioOutputDTOAssembler.toDTO(usuario);
	}

	@PutMapping("/{usuarioId}")
	public UsuarioOutputDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInputDTO usuarioInput) {

		Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);

		usuarioInputDTODisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		
		usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);
		
		return usuarioOutputDTOAssembler.toDTO(usuarioAtual);
	}

	@PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInputDTO senha) {
        cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    } 
}
