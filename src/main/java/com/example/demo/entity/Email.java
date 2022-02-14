package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "email")
public class Email {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "direccion_email", nullable = false, unique = true, length = 50)
	private String direccionEmail;
	
	@ManyToOne
	@JoinColumn(name = "persona_id")
	private Persona persona;

	public Email() {
		super();
	}

	public Email(String direccionEmail) {
		super();
		this.direccionEmail = direccionEmail;
	}

	public Email(String direccionEmail, Persona persona) {
		super();
		this.direccionEmail = direccionEmail;
		this.persona = persona;
	}

	public Email(int id, String direccionEmail, Persona persona) {
		super();
		this.id = id;
		this.direccionEmail = direccionEmail;
		this.persona = persona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccionEmail() {
		return direccionEmail;
	}

	public void setDireccionEmail(String direccionEmail) {
		this.direccionEmail = direccionEmail;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", direccionEmail=" + direccionEmail;
	}
	
	
	
}
