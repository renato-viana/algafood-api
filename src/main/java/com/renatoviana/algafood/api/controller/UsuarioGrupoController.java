package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.modelmapper.assembler.GrupoModelResponseAssembler;
import com.renatoviana.algafood.api.model.response.GrupoModelResponse;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.service.CadastroUsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Grupos de usuários")
@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelResponseAssembler grupoModelResponseAssembler;

    @GetMapping
    public List<GrupoModelResponse> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        return grupoModelResponseAssembler.toCollectionModelResponse(usuario.getGrupos());
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