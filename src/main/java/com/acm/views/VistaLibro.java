package com.acm.views;

public class VistaLibro extends Layout{
    public boolean agregarLibro(){
        System.out.println("Desea agregar un nuevo libro?\n1. Sí\n2. No");
        return digitarOpcion() == 1;
    }
    public String ObtenerAtributo(String valor){
        System.out.println("Digite el valor de " + valor);
        return digitarTexto();
    }
    public static void libroSinResultados(){
        System.out.println("No se encuentra el libro elegido");
    }
    public static void exito(){
        System.out.println("Operación exitosa!");
    }
    public static void libroRepetido(){
        System.out.println("Libro ya elegido, no se puede pedir nuevamente");
    }
}
