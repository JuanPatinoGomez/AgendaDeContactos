package com.example.demo.service.telefono;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Telefono;
import com.example.demo.repository.TelefonoRepository;
import com.example.demo.service.ServiceTelefonoEmail;
import com.example.demo.service.ServicesGeneral;

@Service("servicetelefono")
public class ServiceTelefonoImpl implements ServicesGeneral<Telefono>, ServiceTelefonoEmail<Telefono>{

	@Autowired
	private TelefonoRepository telefonoRepository;
	
	@Override
	public List<Telefono> findAll() {
		
		return this.telefonoRepository.findAll();
	}

	@Override
	public Telefono findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Telefono save(Telefono t) {
		
		return this.telefonoRepository.save(t);
	}

	@Override
	public void deleteById(int id) {
		this.telefonoRepository.deleteById(id);
		
	}

	@Override
	public Telefono update(Telefono t) {
		
		return this.telefonoRepository.save(t);
	}

	@Override
	public List<Telefono> findAllByPersona(Persona persona) {
		return this.telefonoRepository.findAllByPersona(persona);
	}

	@Override
	public List<Telefono> saveAll(List<Telefono> t) {
		
		return this.telefonoRepository.saveAll(t);
	}

}
