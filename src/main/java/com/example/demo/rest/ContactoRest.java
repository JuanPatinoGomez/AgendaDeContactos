package com.example.demo.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Email;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Telefono;
import com.example.demo.modelojsoninformacion.InformacionDeContacto;
import com.example.demo.modelojsoninformacion.PersonaFormato;
import com.example.demo.service.ServiceTelefonoEmail;
import com.example.demo.service.ServicesGeneral;
import com.example.demo.service.persona.ServicePersona;

@RestController
@RequestMapping("/contacto")
public class ContactoRest {
	
	private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Autowired
	ServicePersona servicePersona;
	
	@Autowired
	@Qualifier("servicetelefono")
	private ServiceTelefonoEmail<Telefono> telefonoServicePersona;
	
	@Autowired
	@Qualifier("serviceemail")
	private ServiceTelefonoEmail<Email> emailServicePersona;

	@Autowired
	@Qualifier("servicetelefono")
	private ServicesGeneral<Telefono> servicesGeneralTelefono;

	@Autowired
	@Qualifier("serviceemail")
	private ServicesGeneral<Email> serviceGeneralEmail;
	
	//♠
	
	@PostMapping("/busqueda/nombre")
	public List<PersonaFormato> buscarPersonaPorNombre(@RequestBody Map<String, String> solicitud){

		//Se inicializa el listado de personas formateadas para ser pasadas por json
		List<PersonaFormato> listadoPersonasFormat =  new ArrayList<>();

		//Se llena el listado de personas
		List<Persona> listadoPersonas;

		if(solicitud.get("nombre").equals("all")){ //significa que devolvera todos porque presiono el boton sin ningun valor

			listadoPersonas = this.servicePersona.findAll();

		}else{ //Aqui significa que agrego un valor

			listadoPersonas = this.servicePersona.findAllByNombreContaining(solicitud.get("nombre"));

		}

		//Se agregan al listado de personas formateadas
		for(Persona persona: listadoPersonas){
			listadoPersonasFormat.add(new PersonaFormato(persona.getId(), persona.getNombre()));
		}

		return listadoPersonasFormat;
	}

	@PostMapping("/eliminar/persona")
	public Map<String, String> eliminarPersona(@RequestBody Map<String, Integer> solicitud){
		this.servicePersona.deleteById(solicitud.get("id"));
		
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("resultado", "ok");
		return respuesta;
	}
	
	@PostMapping("/informacion_contacto")
	public InformacionDeContacto devolverInformacionDeContactoPersona(@RequestBody Map<String, Integer> solicitud){
		
		InformacionDeContacto informacionDeContacto = new InformacionDeContacto();
		
		Map<Integer, String> mapaEmails = new HashMap<>(); //Campo de emails
		
		//Rellenamos el mapa de los emails
		for(Email email : this.emailServicePersona.findAllByPersona(new Persona(solicitud.get("id")))) {
			
			mapaEmails.put(email.getId(), email.getDireccionEmail());
			
		}
		
		//Pasamos el mapa 
		informacionDeContacto.setMapaEmails(mapaEmails);
		
		Map<Integer, Long> mapaTelefonos = new HashMap<>();
		
		//Rellenamos el mapa de los telefonos
		for(Telefono telefono: this.telefonoServicePersona.findAllByPersona(new Persona(solicitud.get("id")))) {
			
			mapaTelefonos.put(telefono.getId(), telefono.getNumeroTelefonico());
			
		}
		
		
		//Pasamos el mapa
		informacionDeContacto.setMapaTelefonos(mapaTelefonos);
		
		return informacionDeContacto;
		
	}

	@PostMapping("/eliminar/telefono")
	public Map<String, String> eliminarTelefono(@RequestBody Map<String, Integer> solicitud){

		this.servicesGeneralTelefono.deleteById(solicitud.get("id"));

		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("resultado", "ok");
		return respuesta;
	}

	@PostMapping("/eliminar/email")
	public Map<String, String> eliminarEmail(@RequestBody Map<String, Integer> solicitud){

		this.serviceGeneralEmail.deleteById(solicitud.get("id"));

		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("resultado", "ok");
		return respuesta;
	}

}
