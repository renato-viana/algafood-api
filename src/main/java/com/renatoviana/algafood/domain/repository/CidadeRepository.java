package com.renatoviana.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatoviana.algafood.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
