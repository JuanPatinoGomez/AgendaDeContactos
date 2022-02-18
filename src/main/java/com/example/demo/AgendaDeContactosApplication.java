package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Email;
import com.example.demo.entity.Telefono;
import com.example.demo.service.ServiceTelefonoEmail;
import com.example.demo.service.ServicesGeneral;


@SpringBootApplication
public class AgendaDeContactosApplication{
	
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


}
