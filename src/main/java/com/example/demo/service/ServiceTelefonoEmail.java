package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Persona;

public interface ServiceTelefonoEmail <T>{

	List<T> findAllByPersona(Persona persona);
}
