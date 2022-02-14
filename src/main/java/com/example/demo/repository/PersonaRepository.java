package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{

	List<Persona> findAllByNombreContaining(String nombre);
}
