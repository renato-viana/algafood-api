package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.helper.ResourceLinkHelper;
import com.renatoviana.algafood.api.v1.model.request.ProdutoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.ProdutoModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.ProdutoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.ProdutoModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.ProdutoControllerOpenApi;
import com.renatoviana.algafood.domain.model.Produto;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.ProdutoRepository;
import com.renatoviana.algafood.domain.service.CadastroProdutoService;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @Autowired
    private ResourceLinkHelper resourceLinkHelper;

    @Override
    @GetMapping
    public CollectionModel<ProdutoModelResponse> listar(@PathVariable Long restauranteId, @RequestParam(required =
            false, defaultValue = "false") Boolean incluirInativos) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        List<Produto> produtos = null;

        if (incluirInativos) {
            produtos = produtoRepository.findByRestaurante(restaurante);
        } else {
            produtos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoModelResponseAssembler.toCollectionModel(produtos)
                .add(resourceLinkHelper.linkToProdutos(restauranteId));
    }

    @Override
    @GetMapping("/{produtoId}")
    public ProdutoModelResponse buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        return produtoModelResponseAssembler.toModel(produto);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModelResponse adicionar(@PathVariable Long restauranteId,
                                          @RequestBody @Valid ProdutoModelRequest produtoInput) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        Produto produto = produtoModelRequestDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = cadastroProduto.salvar(produto);

        return produtoModelResponseAssembler.toModel(produto);
    }

    @Override
    @PutMapping("/{produtoId}")
    public ProdutoModelResponse atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                          @RequestBody @Valid ProdutoModelRequest produtoInput) {
        Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        produtoModelRequestDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = cadastroProduto.salvar(produtoAtual);

        return produtoModelResponseAssembler.toModel(produtoAtual);
    }
}   
