package com.renatoviana.algafood.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.renatoviana.algafood.AlgafoodApiApplication;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE)
						.run(args);

		RestauranteRepository restauranteRepository = applicationContext
				.getBean(RestauranteRepository.class);

		Restaurante restaurante = restauranteRepository.buscar(2L);

		System.out.println(restaurante.getNome());
	}

}
