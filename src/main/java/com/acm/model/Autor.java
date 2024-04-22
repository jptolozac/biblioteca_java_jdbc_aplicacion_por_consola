package com.acm.model;

public class Autor {
    int id;
    String nombre;
    String paisOrigen;
    
    public Autor(int id, String nombre, String paisOrigen) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }
    
    public Autor(String nombre, String paisOrigen) {
        id = 0;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
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
    public String getPaisOrigen() {
        return paisOrigen;
    }
    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    @Override
    public String toString() {
        return id + ". nombre: " + nombre + ", paisOrigen: " + paisOrigen + "\n";
    }

    
}
