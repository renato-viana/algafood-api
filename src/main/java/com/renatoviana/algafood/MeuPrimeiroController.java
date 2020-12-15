package com.renatoviana.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renatoviana.algafood.modelo.Cliente;
import com.renatoviana.algafood.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {

	private AtivacaoClienteService ativacaoClienteService;

	public MeuPrimeiroController(
			AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;

		System.out.println("MeuPrimeiroController: " + ativacaoClienteService);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {

		Cliente joao = new Cliente("João", "joao@xyz.com", "3499998888");

		ativacaoClienteService.ativar(joao);

		return "Hello!";
	}
}
