package com.renatoviana.algafood.jpa.restaurante;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.renatoviana.algafood.AlgafoodApiApplication;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE)
						.run(args);

		RestauranteRepository restauranteRepository = applicationContext
				.getBean(RestauranteRepository.class);

		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Càm O'n - Thai Food");
		restaurante1.setTaxaFrete(new BigDecimal(15.0));

		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Maé Noi");
		restaurante2.setTaxaFrete(new BigDecimal(13.0));

		restaurante1 = restauranteRepository.salvar(restaurante1);
		restaurante2 = restauranteRepository.salvar(restaurante2);

		System.out.printf("%d - %s\n", restaurante1.getId(),
				restaurante1.getNome());
		System.out.printf("%d - %s\n", restaurante2.getId(),
				restaurante2.getNome());
	}

}
