package com.renatoviana.algafood.api.v1.controller;

import com.renatoviana.algafood.api.v1.openapi.controller.StatusPedidoControllerOpenApi;
import com.renatoviana.algafood.core.security.CheckSecurity;
import com.renatoviana.algafood.domain.service.StatusPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/pedidos/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusPedidoController implements StatusPedidoControllerOpenApi {

    @Autowired
    StatusPedidoService statusPedidoService;

    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @Override
    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido) {
        statusPedidoService.confirmar(codigoPedido);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @Override
    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codigoPedido) {
        statusPedidoService.entregar(codigoPedido);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @Override
    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido) {
        statusPedidoService.cancelar(codigoPedido);

        return ResponseEntity.noContent().build();
    }

}
