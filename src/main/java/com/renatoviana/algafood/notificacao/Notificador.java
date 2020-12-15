package com.renatoviana.algafood.notificacao;

import com.renatoviana.algafood.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}