package com.example.demo.modelojsoninformacion;

public class TelefonoFormato {
    
    private int id;
    private long numeroTelefonico;

    
    public TelefonoFormato() {
    }
    public TelefonoFormato(int id, long numeroTelefonico) {
        this.id = id;
        this.numeroTelefonico = numeroTelefonico;
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

    
}
