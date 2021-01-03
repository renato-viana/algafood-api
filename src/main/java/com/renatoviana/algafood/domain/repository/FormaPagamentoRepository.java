package com.renatoviana.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatoviana.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository
		extends JpaRepository<FormaPagamento, Long> {

}
