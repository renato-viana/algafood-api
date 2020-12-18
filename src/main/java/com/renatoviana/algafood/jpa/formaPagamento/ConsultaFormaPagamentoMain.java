package com.renatoviana.algafood.jpa.formaPagamento;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.renatoviana.algafood.AlgafoodApiApplication;
import com.renatoviana.algafood.domain.model.FormaPagamento;
import com.renatoviana.algafood.domain.repository.FormaPagamentoRepository;

public class ConsultaFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE)
						.run(args);

		FormaPagamentoRepository formaPagamentoRepository = applicationContext
				.getBean(FormaPagamentoRepository.class);

		List<FormaPagamento> formasPagamentos = formaPagamentoRepository
				.listar();

		for (FormaPagamento formaPagamento : formasPagamentos) {
			System.out.println(formaPagamento.getDescricao());
		}
	}

}
