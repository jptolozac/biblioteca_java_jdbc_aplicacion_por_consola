package com.acm.model;

public class Cliente {
    String cedula;
    String nombre;
    String correo;
    String telefono;
    double estadoDeCuenta;
    public Cliente(String cedula, String nombre, String correo, String telefono, double estadoDeCuenta) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.estadoDeCuenta = estadoDeCuenta;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public double getEstadoDeCuenta() {
        return estadoDeCuenta;
    }
    public void setEstadoDeCuenta(double estadoDeCuenta) {
        this.estadoDeCuenta = estadoDeCuenta;
    }
    @Override
    public String toString() {
        return "Cliente [cedula=" + cedula + ", nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono
                + ", estadoDeCuenta=" + estadoDeCuenta + "]";
    }
    
}