package com.renatoviana.algafood.domain.repository;

import java.util.List;

import com.renatoviana.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();

	Restaurante buscar(Long id);

	Restaurante salvar(Restaurante restaurante);

	void remover(Long id);

}
