package com.renatoviana.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.renatoviana.algafood.notificacao.NivelUrgencia;
import com.renatoviana.algafood.notificacao.Notificador;
import com.renatoviana.algafood.notificacao.TipoDoNotificador;
import com.renatoviana.algafood.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	
	@TipoDoNotificador(NivelUrgencia.NORMAL)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
	}

}
