package com.example.demo.service.persona;

import java.util.List;

import com.example.demo.entity.Persona;
import com.example.demo.service.ServicesGeneral;

public interface ServicePersona extends ServicesGeneral<Persona>{
	
	List<Persona> findAllByNombreContaining(String nombre);
	
}
