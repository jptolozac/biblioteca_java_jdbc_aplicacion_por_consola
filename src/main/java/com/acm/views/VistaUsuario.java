package com.acm.views;

import java.util.List;

import com.acm.model.Libro;

public  class VistaUsuario extends Layout {
    public void mostrarLibros(List<Libro> libros){
        String mostrar = "";
        int i = 0;
        for(Libro libro : libros){
            mostrar += "\t" + ++i + ". " + libro.getTitulo() + "\n";
        }
        imprimir(mostrar);
    }

    public boolean continuar(){
        imprimir("1. Seguir con los datos ya almacenados\n2. Empezar llenando los datos nuevamente");
        return digitarOpcion() == 1;
    }

    public int ingresar(){
        imprimir("1. ingresar como admin\n2. ingresar como cliente\n\nSi no está registrado, ingrese 0");
        return digitarOpcion();
    }

    public String getNombreUsuario(){
        imprimir("Digite su nombre de usuario (cedula para clientes o código (1234) para admins)");
        return digitarTexto();
    }
}
