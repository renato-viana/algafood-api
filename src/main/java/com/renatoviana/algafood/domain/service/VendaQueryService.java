package com.renatoviana.algafood.domain.service;

import com.renatoviana.algafood.domain.filter.VendaDiariaFilter;
import com.renatoviana.algafood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
