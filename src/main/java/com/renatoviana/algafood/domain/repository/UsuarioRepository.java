package com.renatoviana.algafood.domain.repository;

import java.util.Optional;

import com.renatoviana.algafood.domain.model.Usuario;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
}
