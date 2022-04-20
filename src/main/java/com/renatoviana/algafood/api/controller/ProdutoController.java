package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.assembler.ProdutoInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.ProdutoOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.ProdutoInputDTO;
import com.renatoviana.algafood.api.model.dto.output.ProdutoOutputDTO;
import com.renatoviana.algafood.domain.model.Produto;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.ProdutoRepository;
import com.renatoviana.algafood.domain.service.CadastroProdutoService;
import com.renatoviana.algafood.domain.service.CadastroRestauranteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private ProdutoOutputDTOAssembler produtoOutputDTOAssembler;

    @Autowired
    private ProdutoInputDTODisassembler produtoInputDTODisassembler;

    @GetMapping
    public List<ProdutoOutputDTO> listar(@PathVariable Long restauranteId, @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        List<Produto> produtos = null;

        if (incluirInativos) {
            produtos = produtoRepository.findByRestaurante(restaurante);
        } else {
            produtos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoOutputDTOAssembler.toCollectionDTO(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoOutputDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        return produtoOutputDTOAssembler.toDTO(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoOutputDTO adicionar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid ProdutoInputDTO produtoInput) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDTODisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = cadastroProduto.salvar(produto);

        return produtoOutputDTOAssembler.toDTO(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoOutputDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                      @RequestBody @Valid ProdutoInputDTO produtoInput) {
        Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDTODisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = cadastroProduto.salvar(produtoAtual);

        return produtoOutputDTOAssembler.toDTO(produtoAtual);
    }
}   
