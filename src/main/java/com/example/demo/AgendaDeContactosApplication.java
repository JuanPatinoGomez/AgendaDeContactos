package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Email;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Telefono;
import com.example.demo.service.ServiceTelefonoEmail;
import com.example.demo.service.ServicesGeneral;
import com.example.demo.service.persona.ServicePersona;

@SpringBootApplication
public class AgendaDeContactosApplication implements CommandLineRunner{
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ServicePersona servicePersona;
	
	@Autowired
	@Qualifier("serviceemail")
	private ServicesGeneral<Email> emailServicesGeneral;
	
	@Autowired
	@Qualifier("servicetelefono")
	private ServicesGeneral<Telefono> telefonoServicesGeneral;
	
	@Autowired
	@Qualifier("serviceemail")
	private ServiceTelefonoEmail<Email> emailServicePersona;
	
	public static void main(String[] args) {
		SpringApplication.run(AgendaDeContactosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		this.servicePersona.save(new Persona("Lucio Souto"));
		this.servicePersona.save(new Persona("Yasmin Miranda"));
		this.servicePersona.save(new Persona("Juan Ignacio Hoyos"));
		this.servicePersona.save(new Persona("Serafina Maldonado"));
		this.servicePersona.save(new Persona("Alma Barranco"));
		this.servicePersona.save(new Persona("Antonio Jose Rovira"));
		this.servicePersona.save(new Persona("Omar Nadal"));
		this.servicePersona.save(new Persona("Juan Patino"));
		this.servicePersona.save(new Persona("Maria Rondon"));
		
		Email email1 = new Email("direccion1@gmail.com", new Persona(1));
		Email email2 = new Email("direccion2@gmail.com", new Persona(1));
		
		this.emailServicesGeneral.save(email1);
		this.emailServicesGeneral.save(email2);
		
		Telefono telefono1 = new Telefono(153568546, new Persona(1));
		
		this.telefonoServicesGeneral.save(telefono1);
		
		//--------------------------------------------------------------
		Persona persona = new Persona("Rodrigo");
		ArrayList<Email> emailsDeRodrigo = new ArrayList();
		emailsDeRodrigo.add(new Email("rodrigo1@gmail.com", persona));
		emailsDeRodrigo.add(new Email("rodrigo2@gmail.com", persona));
		
		ArrayList<Telefono> telefonosDeRodrigo = new ArrayList();
		telefonosDeRodrigo.add(new Telefono(3188588, persona));
		telefonosDeRodrigo.add(new Telefono(35215151, persona));
		
		persona.setEmails(emailsDeRodrigo);
		persona.setTelefonos(telefonosDeRodrigo);
		
		this.servicePersona.save(persona);
		*/
		
		/*
		Persona persona = this.servicePersona.findById(1);
		
		System.out.println(persona.getNombre());
		System.out.println(persona.getEmails());
		*/

		/*
		this.log.info("persona");
		for(Persona persona : this.servicePersona.findAll()) {
			log.info(persona.getNombre());
		}
		
		
		this.log.info("emails");
		for(Email email : this.emailServicesGeneral.findAll()) {
			log.info(email.getDireccionEmail());
		}
		
		
		this.log.info("emails2---");
		for(Email email : this.emailServicePersona.findAllByPersona(new Persona(1))) {
			log.info(email.toString());
		}
		*/
	}

}
