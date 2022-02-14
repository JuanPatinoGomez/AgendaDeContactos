package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "nombre", nullable = false, unique = false, length = 40)
	private String nombre;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private List<Email> emails;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private List<Telefono> telefonos;

	
	public Persona() {
		super();
	}

	public Persona(int id) {
		super();
		this.id = id;
	}

	public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}


	public Persona(String nombre, List<Email> emails, List<Telefono> telefonos) {
		super();
		this.nombre = nombre;
		this.emails = emails;
		this.telefonos = telefonos;
	}

	public Persona(int id, String nombre, List<Email> emails, List<Telefono> telefonos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.emails = emails;
		this.telefonos = telefonos;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Email> getEmails() {
		return emails;
	}


	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}


	public List<Telefono> getTelefonos() {
		return telefonos;
	}


	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", emails=" + emails + ", telefonos=" + telefonos + "]";
	}
	
	
	
}
