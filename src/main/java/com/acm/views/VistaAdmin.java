package com.acm.views;

public class VistaAdmin extends VistaUsuario{
    public int Menu(){
        String texto = "1. Ingresar nuevo libro"
                    + "\n2. Actualizar información de un libro"
                    + "\n3. Eliminar libro";
        imprimir(texto);
        return digitarOpcion();
    }
}
