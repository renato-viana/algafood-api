package com.renatoviana.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatoviana.algafood.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
