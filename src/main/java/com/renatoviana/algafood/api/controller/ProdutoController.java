package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.model.request.ProdutoModelRequest;
import com.renatoviana.algafood.api.model.response.ProdutoModelResponse;
import com.renatoviana.algafood.api.modelmapper.assembler.ProdutoModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.disassembler.ProdutoModelRequestDisassembler;
import com.renatoviana.algafood.api.openapi.controller.ProdutoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Produto;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.ProdutoRepository;
import com.renatoviana.algafood.domain.service.CadastroProdutoService;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController implements ProdutoControllerOpenApi {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private ProdutoModelResponseAssembler produtoModelResponseAssembler;

    @Autowired
    private ProdutoModelRequestDisassembler produtoModelRequestDisassembler;

    @GetMapping
    public List<ProdutoModelResponse> listar(@PathVariable Long restauranteId, @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        List<Produto> produtos = null;

        if (incluirInativos) {
            produtos = produtoRepository.findByRestaurante(restaurante);
        } else {
            produtos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoModelResponseAssembler.toCollectionModelResponse(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModelResponse buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        return produtoModelResponseAssembler.toModelResponse(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModelResponse adicionar(@PathVariable Long restauranteId,
                                          @RequestBody @Valid ProdutoModelRequest produtoInput) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        Produto produto = produtoModelRequestDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = cadastroProduto.salvar(produto);

        return produtoModelResponseAssembler.toModelResponse(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModelResponse atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                          @RequestBody @Valid ProdutoModelRequest produtoInput) {
        Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        produtoModelRequestDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = cadastroProduto.salvar(produtoAtual);

        return produtoModelResponseAssembler.toModelResponse(produtoAtual);
    }
}   
