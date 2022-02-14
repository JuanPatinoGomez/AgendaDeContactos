package com.example.demo.modelojsoninformacion;

public class EmailFormato {


	private int id;
	private String direccionEmail;
    
    public EmailFormato() {
    }
    public EmailFormato(int id, String direccionEmail) {
        this.id = id;
        this.direccionEmail = direccionEmail;
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

    
    
}
