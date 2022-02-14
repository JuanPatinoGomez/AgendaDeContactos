package com.example.demo.service.email;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Email;
import com.example.demo.entity.Persona;
import com.example.demo.repository.EmailRepository;
import com.example.demo.service.ServiceTelefonoEmail;
import com.example.demo.service.ServicesGeneral;

@Service("serviceemail")
public class ServicesEmailImpl implements ServicesGeneral<Email>, ServiceTelefonoEmail<Email>{

	@Autowired
	private EmailRepository emailRepository;
	
	@Override
	public List<Email> findAll() {
		return this.emailRepository.findAll();
	}

	@Override
	public Email findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Email save(Email t) {

		return this.emailRepository.save(t);
	}

	@Override
	public void deleteById(int id) {
		this.emailRepository.deleteById(id);
		
	}

	@Override
	public Email update(Email t) {
		
		return this.emailRepository.save(t);
	}

	@Override
	public List<Email> findAllByPersona(Persona persona) {
		return this.emailRepository.findAllByPersona(persona);
	}

	@Override
	public List<Email> saveAll(List<Email> t) {
		
		return this.emailRepository.saveAll(t);
	}

}
