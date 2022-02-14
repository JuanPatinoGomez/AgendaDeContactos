package com.example.demo.modelojsoninformacion;

import java.util.Map;

public class InformacionDeContacto {
	
	private Map<Integer, String> mapaEmails;
	private Map<Integer, Long> mapaTelefonos;

	public InformacionDeContacto() {
		super();
	}

	
	public InformacionDeContacto(Map<Integer, String> mapaEmails, Map<Integer, Long> mapaTelefonos) {
		super();
		this.mapaEmails = mapaEmails;
		this.mapaTelefonos = mapaTelefonos;
	}


	public Map<Integer, String> getMapaEmails() {
		return mapaEmails;
	}

	public void setMapaEmails(Map<Integer, String> mapaEmails) {
		this.mapaEmails = mapaEmails;
	}


	public Map<Integer, Long> getMapaTelefonos() {
		return mapaTelefonos;
	}

	public void setMapaTelefonos(Map<Integer, Long> mapaTelefonos) {
		this.mapaTelefonos = mapaTelefonos;
	}

	
	
}
