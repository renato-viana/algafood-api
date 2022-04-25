package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.model.request.SenhaModelRequest;
import com.renatoviana.algafood.api.model.request.UsuarioComSenhaModelRequest;
import com.renatoviana.algafood.api.model.request.UsuarioModelRequest;
import com.renatoviana.algafood.api.model.response.UsuarioModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.UsuarioModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.disassembler.UsuarioModelRequestDisassembler;
import com.renatoviana.algafood.api.openapi.controller.UsuarioControllerOpenApi;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.repository.UsuarioRepository;
import com.renatoviana.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioModelResponseAssembler usuarioOutputDTOAssembler;

    @Autowired
    private UsuarioModelRequestDisassembler usuarioInputDTODisassembler;

    @GetMapping
    public List<UsuarioModelResponse> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarioOutputDTOAssembler.toCollectionDTO(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModelResponse buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        return usuarioOutputDTOAssembler.toDTO(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModelResponse adicionar(@RequestBody @Valid UsuarioComSenhaModelRequest usuarioInput) {
        Usuario usuario = usuarioInputDTODisassembler.toDomainObject(usuarioInput);

        usuario = cadastroUsuarioService.salvar(usuario);

        return usuarioOutputDTOAssembler.toDTO(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModelResponse atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioModelRequest usuarioInput) {

        Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        usuarioInputDTODisassembler.copyToDomainObject(usuarioInput, usuarioAtual);

        usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);

        return usuarioOutputDTOAssembler.toDTO(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaModelRequest senha) {
        cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
