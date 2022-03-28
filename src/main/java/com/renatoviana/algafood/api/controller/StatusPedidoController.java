package com.renatoviana.algafood.api.controller;

import com.renatoviana.algafood.api.assembler.PedidoInputDTODisassembler;
import com.renatoviana.algafood.api.assembler.PedidoOutputDTOAssembler;
import com.renatoviana.algafood.api.assembler.PedidoResumoOutputDTOAssembler;
import com.renatoviana.algafood.api.model.dto.input.PedidoInputDTO;
import com.renatoviana.algafood.api.model.dto.output.PedidoOutputDTO;
import com.renatoviana.algafood.api.model.dto.output.PedidoResumoOutputDTO;
import com.renatoviana.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.exception.NegocioException;
import com.renatoviana.algafood.domain.model.Pedido;
import com.renatoviana.algafood.domain.model.Usuario;
import com.renatoviana.algafood.domain.repository.PedidoRepository;
import com.renatoviana.algafood.domain.service.EmissaoPedidoService;
import com.renatoviana.algafood.domain.service.StatusPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}")
public class StatusPedidoController {

    @Autowired
    StatusPedidoService statusPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigoPedido) {
        statusPedidoService.confirmar(codigoPedido);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable String codigoPedido) {
        statusPedidoService.entregar(codigoPedido);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable String codigoPedido) {
        statusPedidoService.cancelar(codigoPedido);
    }
}
