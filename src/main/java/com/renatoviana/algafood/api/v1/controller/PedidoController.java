package com.renatoviana.algafood.api.v1.controller;

import com.google.common.collect.ImmutableMap;
import com.renatoviana.algafood.api.v1.model.request.PedidoModelRequest;
import com.renatoviana.algafood.api.v1.model.response.PedidoModelResponse;
import com.renatoviana.algafood.api.v1.model.response.PedidoResumoModelResponse;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.PedidoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.assembler.PedidoResumoModelResponseAssembler;
import com.renatoviana.algafood.api.v1.modelmapper.disassembler.PedidoModelRequestDisassembler;
import com.renatoviana.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.renatoviana.algafood.core.data.PageWrapper;
import com.renatoviana.algafood.core.data.PageableTranslator;
import com.renatoviana.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.filter.PedidoFilter;
import com.renatoviana.algafood.domain.model.Pedido;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.repository.PedidoRepository;
import com.renatoviana.algafood.domain.service.EmissaoPedidoService;
import com.renatoviana.algafood.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoModelResponseAssembler pedidoModelResponseAssembler;

    @Autowired
    private PedidoResumoModelResponseAssembler pedidoResumoModelResponseAssembler;

    @Autowired
    private PedidoModelRequestDisassembler pedidoModelRequestDisassembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<PedidoResumoModelResponse> pesquisar(PedidoFilter filtro,
                                                           @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelResponseAssembler);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModelResponse buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        return pedidoModelResponseAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModelResponse adicionar(@Valid @RequestBody PedidoModelRequest pedidoModelRequest) {
        try {
            Pedido novoPedido = pedidoModelRequestDisassembler.toDomainObject(pedidoModelRequest);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);

            return pedidoModelResponseAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"

        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
