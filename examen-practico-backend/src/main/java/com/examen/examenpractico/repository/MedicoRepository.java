package com.examen.examenpractico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.examenpractico.model.Medico;
@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long>{

}
