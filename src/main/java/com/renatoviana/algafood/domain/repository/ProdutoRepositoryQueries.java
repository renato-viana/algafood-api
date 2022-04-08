package com.renatoviana.algafood.domain.repository;

import com.renatoviana.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);
}
