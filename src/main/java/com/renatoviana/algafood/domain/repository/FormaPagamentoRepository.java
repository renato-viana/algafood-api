package com.renatoviana.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.renatoviana.algafood.domain.model.FormaPagamento;

import java.time.OffsetDateTime;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    @Query("select max(dataAtualizacao) from FormaPagamento")
    OffsetDateTime getDataUltimaAtualizacao();

    @Query("select dataAtualizacao from FormaPagamento where id = :formaPagamentoId")
    OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);
}
