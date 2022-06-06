package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.model.request.SenhaModelRequest;
import com.renatoviana.algafood.api.v1.model.request.UsuarioComSenhaModelRequest;
import com.renatoviana.algafood.api.v1.model.request.UsuarioModelRequest;
import com.renatoviana.algafood.api.v1.model.response.UsuarioModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.UsuarioModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.UsuarioModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.repository.UsuarioRepository;
import com.renatoviana.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioModelResponseAssembler usuarioModelResponseAssembler;

    @Autowired
    private UsuarioModelRequestDisassembler usuarioModelRequestDisassembler;

    @GetMapping
    public CollectionModel<UsuarioModelResponse> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarioModelResponseAssembler.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModelResponse buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        return usuarioModelResponseAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModelResponse adicionar(@RequestBody @Valid UsuarioComSenhaModelRequest usuarioInput) {
        Usuario usuario = usuarioModelRequestDisassembler.toDomainObject(usuarioInput);

        usuario = cadastroUsuarioService.salvar(usuario);

        return usuarioModelResponseAssembler.toModel(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModelResponse atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioModelRequest usuarioInput) {

        Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        usuarioModelRequestDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);

        usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);

        return usuarioModelResponseAssembler.toModel(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaModelRequest senha) {
        cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
