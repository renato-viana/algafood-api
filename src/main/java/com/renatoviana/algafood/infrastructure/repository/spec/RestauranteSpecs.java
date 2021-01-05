package com.renatoviana.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.renatoviana.algafood.domain.model.Restaurante;

public class RestauranteSpecs {

	public static Specification<Restaurante> comfreteGratis() {
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"),
				BigDecimal.ZERO);
	}

	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, builder) -> builder.like(root.get("nome"),
				"%" + nome + "%");
	}
}
