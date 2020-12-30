package com.renatoviana.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatoviana.algafood.domain.exception.EntidadeEmUsoException;
import com.renatoviana.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.model.Estado;
import com.renatoviana.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}

	public void excluir(Long id) {
		try {

			estadoRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(
					"Não existe um cadastro de estado com código %d!", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Estado de código %d não pode ser removida, pois está em uso!",
					id));
		}
	}
}
