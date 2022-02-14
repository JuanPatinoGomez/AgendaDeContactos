package com.example.demo.service.persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;

@Service("serviciopersona")
public class ServicesPersonaImpl implements ServicePersona{

	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public List<Persona> findAll() {
		return this.personaRepository.findAll();
	}

	@Override
	public Persona findById(int id) {
		
		return this.personaRepository.findById(id).get();
	}

	@Override
	public Persona save(Persona t) {
		
		return this.personaRepository.save(t);
	}

	@Override
	public void deleteById(int id) {
		this.personaRepository.deleteById(id);
		
	}

	@Override
	public Persona update(Persona t) {
		return this.personaRepository.save(t);
	}

	@Override
	public List<Persona> findAllByNombreContaining(String nombre) {
		
		return this.personaRepository.findAllByNombreContaining(nombre);
	}

	@Override
	public List<Persona> saveAll(List<Persona> t) {
		
		return this.personaRepository.saveAll(t);
	}

}
