package com.renatoviana.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renatoviana.algafood.domain.model.Restaurante;

public interface RestauranteRepository
		extends CustomJpaRepository<Restaurante, Long>,
		RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	@Query("from Restaurante r join r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial,
			BigDecimal taxaFinal);

//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome,
			@Param("id") Long cozinhaId);

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome,
//			Long cozinhaId);

	Optional<Restaurante> findFirstByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);

}
