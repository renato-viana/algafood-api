package com.renatoviana.algafood.api.controller;

import com.google.common.collect.ImmutableMap;
import com.renatoviana.algafood.api.modelmapper.disassembler.PedidoModelRequestDisassembler;
import com.renatoviana.algafood.api.modelmapper.assembler.PedidoModelResponseAssembler;
import com.renatoviana.algafood.api.modelmapper.assembler.PedidoResumoModelResponseAssembler;
import com.renatoviana.algafood.api.model.request.PedidoModelRequest;
import com.renatoviana.algafood.api.model.response.PedidoModelResponse;
import com.renatoviana.algafood.api.model.response.PedidoResumoModelResponse;
import com.renatoviana.algafood.core.data.PageableTranslator;
import com.renatoviana.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.filter.PedidoFilter;
import com.renatoviana.algafood.domain.model.Pedido;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.repository.PedidoRepository;
import com.renatoviana.algafood.domain.service.EmissaoPedidoService;
import com.renatoviana.algafood.infrastructure.repository.spec.PedidoSpecs;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Pedidos")
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

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

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        List<PedidoResumoOutputDTO> pedidosOutputDto = pedidoResumoModelResponseAssembler.toCollectionDTO(pedidos);
//
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosOutputDto);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if (StringUtils.isNotBlank(campos)) {
//            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//        pedidosWrapper.setFilters(filterProvider);
//
//        return pedidosWrapper;
//    }

    @GetMapping
    public Page<PedidoResumoModelResponse> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoModelResponse> pedidosResumoOutput =
                pedidoResumoModelResponseAssembler.toCollectionModelResponse(pedidosPage.getContent());

        Page<PedidoResumoModelResponse> pedidosResumoOutputPage =
                new PageImpl<>(pedidosResumoOutput, pageable, pedidosPage.getTotalElements());

        return pedidosResumoOutputPage;
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModelResponse buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        return pedidoModelResponseAssembler.toModelResponse(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModelResponse adicionar(@Valid @RequestBody PedidoModelRequest pedidoInput) {
        try {
            Pedido novoPedido = pedidoModelRequestDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);

            return pedidoModelResponseAssembler.toModelResponse(novoPedido);
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
