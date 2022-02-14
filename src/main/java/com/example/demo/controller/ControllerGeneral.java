package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.entity.Email;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Telefono;
import com.example.demo.modelojsoninformacion.EmailFormato;
import com.example.demo.modelojsoninformacion.PersonaFormato;
import com.example.demo.modelojsoninformacion.TelefonoFormato;
import com.example.demo.service.ServiceTelefonoEmail;
import com.example.demo.service.ServicesGeneral;
import com.example.demo.service.persona.ServicePersona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerGeneral {

	@Autowired
	private ServicePersona servicePersona;

	@Autowired
	@Qualifier("servicetelefono")
	private ServiceTelefonoEmail<Telefono> serviceTelefono;

	@Autowired
	@Qualifier("serviceemail")
	private ServiceTelefonoEmail<Email> serviceEmail;

	@Autowired
	@Qualifier("servicetelefono")
	private ServicesGeneral<Telefono> servicesGeneralTelefono;

	@Autowired
	@Qualifier("serviceemail")
	private ServicesGeneral<Email> serviceGeneralEmail;

	@GetMapping(path = { "/", "/agenda" })
	public ModelAndView inicioListadoContactos() {

		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("contactos", this.servicePersona.findAll());
		return modelAndView;
	}

	@PostMapping("/guardar")
	public String guardarContacto(@ModelAttribute PersonaFormato personaformateada, HttpServletRequest request) {

		
		

		// Construir la persona
		Persona persona = new Persona();
		persona.setId(personaformateada.getId());
		persona.setNombre(personaformateada.getNombre());
		// Almacenamos la persona en la base de datos
		this.servicePersona.save(persona);

		// Verificamos si se enviaron telefonos
		if (request.getParameterValues("telefonos") != null) {

			String[] idTelefonos = request.getParameterValues("idtelefonos");
			String[] telefonos = request.getParameterValues("telefonos");

			// Construir los telefonos

			List<Telefono> listadoDeTelefonos = new ArrayList<>();

			for (int i = 0; i < telefonos.length; i++) {

				if (telefonos[i].length() > 0) {// continuar si el campo no esta vacio

					Telefono telefono = new Telefono();
					telefono.setId(Integer.parseInt(idTelefonos[i]));
					telefono.setNumeroTelefonico(Long.parseLong(telefonos[i]));
					telefono.setPersona(persona);

					listadoDeTelefonos.add(telefono);

				}

			}
			//Almacenamos los telefonos en la base de datos
			this.servicesGeneralTelefono.saveAll(listadoDeTelefonos);

		}

		// Verificamos si se enviaron emails
		if (request.getParameterValues("emails") != null) {

			String[] idEmails = request.getParameterValues("idemails");
			String[] emails = request.getParameterValues("emails");

			// Construir los emails

			List<Email> listadoDeEmails = new ArrayList<>();

			for (int i = 0; i < emails.length; i++) {

				if (emails[i].length() > 0) {

					Email email = new Email();
					email.setId(Integer.parseInt(idEmails[i]));
					email.setDireccionEmail(emails[i]);
					email.setPersona(persona);

					listadoDeEmails.add(email);
				}

			}
			//Almacenamos los emails en la base de datos
			this.serviceGeneralEmail.saveAll(listadoDeEmails);

		}

		// ♠

		

		return "redirect:/agenda";
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevoContacto() {

		ModelAndView modelAndView = new ModelAndView("nuevo");
		modelAndView.addObject("contacto", new PersonaFormato());

		return modelAndView;

	}

	@GetMapping("/editar/{id}")
	public ModelAndView editarContacto(@PathVariable("id") int id) {

		ModelAndView modelAndView = new ModelAndView("nuevo");

		Persona persona = this.servicePersona.findById(id);
		// Agregar personas
		PersonaFormato personaFormateada = new PersonaFormato();
		personaFormateada.setId(persona.getId());
		personaFormateada.setNombre(persona.getNombre());

		modelAndView.addObject("contacto", personaFormateada);

		List<TelefonoFormato> listadoTelefonosFormateados = new ArrayList<>();
		List<EmailFormato> listadoEmailsFormateados = new ArrayList<>();

		// Agregar telefonos
		for (Telefono telefono : this.serviceTelefono.findAllByPersona(new Persona(id))) {

			TelefonoFormato telefonoFormateado = new TelefonoFormato();
			telefonoFormateado.setId(telefono.getId());
			telefonoFormateado.setNumeroTelefonico(telefono.getNumeroTelefonico());

			listadoTelefonosFormateados.add(telefonoFormateado);
		}

		// Agregar Emails
		for (Email email : this.serviceEmail.findAllByPersona(new Persona(id))) {

			EmailFormato emailFormateado = new EmailFormato();
			emailFormateado.setId(email.getId());
			emailFormateado.setDireccionEmail(email.getDireccionEmail());

			listadoEmailsFormateados.add(emailFormateado);
		}

		modelAndView.addObject("tels", listadoTelefonosFormateados);
		modelAndView.addObject("emls", listadoEmailsFormateados);
		return modelAndView;
	}

}
