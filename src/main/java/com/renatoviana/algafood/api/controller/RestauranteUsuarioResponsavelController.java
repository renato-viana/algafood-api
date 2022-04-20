package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.modelmapper.assembler.UsuarioModelResponseAssembler;
import com.renatoviana.algafood.api.model.response.UsuarioModelResponse;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Usuários responsáveis dos restaurantes")
@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    
    @Autowired
    private UsuarioModelResponseAssembler usuarioOutputDTOAssembler;
    
    @GetMapping
    public List<UsuarioModelResponse> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        
        return usuarioOutputDTOAssembler.toCollectionDTO(restaurante.getResponsaveis());
    }
    
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
    }
    
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
    }
}
