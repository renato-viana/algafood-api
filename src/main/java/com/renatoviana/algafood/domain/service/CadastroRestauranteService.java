package com.renatoviana.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatoviana.algafood.domain.exception.EntidadeEmUsoException;
import com.renatoviana.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.model.Cozinha;
import com.renatoviana.algafood.domain.model.Restaurante;
import com.renatoviana.algafood.domain.repository.CozinhaRepository;
import com.renatoviana.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

		if (cozinha == null)
			throw new EntidadeNaoEncontradaException(String.format(
					"Não existe cadastro de cozinha com código %d", cozinhaId));

		restaurante.setCozinha(cozinha);

		return restauranteRepository.salvar(restaurante);
	}

	public void excluir(Long id) {
		try {

			restauranteRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(
					"Não existe um cadastro de restaurante com código %d!",
					id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Restaurante de código %d não pode ser removida, pois está em uso!",
					id));
		}
	}
}
