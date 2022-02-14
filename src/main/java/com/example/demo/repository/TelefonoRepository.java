package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Telefono;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Integer>{

	List<Telefono> findAllByPersona(Persona persona);
}
