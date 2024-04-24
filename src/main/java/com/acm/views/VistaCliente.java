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
    public static String[] pedirDatos(){
        String[] datos = new String[5];
        Layout ly = new Layout();
        System.out.println("Digite su numero de cedula");
        datos[0] = ly.digitarTexto();
        System.out.println("Digite su nombre");
        datos[1] = ly.digitarTexto();
        System.out.println("Digite su correo");
        datos[2] = ly.digitarTexto();
        System.out.println("Digite su numero de telefono");
        datos[3] = ly.digitarTexto();
        datos[4] = "0";

        return datos;
    }
    
}
