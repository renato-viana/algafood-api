package com.renatoviana.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatoviana.algafood.domain.exception.EntidadeEmUsoException;
import com.renatoviana.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.renatoviana.algafood.domain.model.Cidade;
import com.renatoviana.algafood.domain.model.Estado;
import com.renatoviana.algafood.domain.repository.CidadeRepository;
import com.renatoviana.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String
						.format("Não existe cadastro de estado com código %d",
								estadoId)));

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	public void excluir(Long id) {
		try {

			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(
					"Não existe um cadastro de cidade com código %d!", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Cidade de código %d não pode ser removida, pois está em uso!",
					id));
		}
	}
}
