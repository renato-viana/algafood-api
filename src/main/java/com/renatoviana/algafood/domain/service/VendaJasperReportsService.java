package com.renatoviana.algafood.domain.service;

import com.renatoviana.algafood.domain.filter.VendaDiariaFilter;
import com.renatoviana.algafood.domain.model.dto.VendaDiaria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendaJasperReportsService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
