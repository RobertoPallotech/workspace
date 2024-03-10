package com.examen.examenpractico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.examenpractico.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
}
