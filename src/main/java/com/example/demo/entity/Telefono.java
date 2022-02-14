package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "telefono")
public class Telefono {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "numero_telefonico", nullable = false, unique = true, length = 11)
	private long numeroTelefonico;
	
	@ManyToOne
	@JoinColumn(name = "persona_id")
	private Persona persona;

	public Telefono() {
		super();
	}

	public Telefono(long numeroTelefonico) {
		super();
		this.numeroTelefonico = numeroTelefonico;
	}

	public Telefono(long numeroTelefonico, Persona persona) {
		super();
		this.numeroTelefonico = numeroTelefonico;
		this.persona = persona;
	}

	public Telefono(int id, long numeroTelefonico, Persona persona) {
		super();
		this.id = id;
		this.numeroTelefonico = numeroTelefonico;
		this.persona = persona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(long numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Telefono [id=" + id + ", numeroTelefonico=" + numeroTelefonico;
	}
	
	

}
