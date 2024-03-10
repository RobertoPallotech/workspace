package com.examen.examenpractico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.examenpractico.model.Paciente;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long>{

}
