package com.acm.views;

public class VistaCliente extends VistaUsuario{

    public int Menu() {
        imprimir("1. Pedir prestamo de libros");
        return digitarOpcion();
    }
    public boolean pedirOtroLibro(){
        imprimir("1. Pedir otro libro\n2. Terminar");
        return digitarOpcion() == 1;
    }
    public int diasPrestamo(){
        imprimir("Digite los días que quiere tener los libros a solicitar");
        return digitarOpcion();
    }
    
}
