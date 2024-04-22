package com.acm.views;

import java.util.Scanner;

public class Layout {
    Scanner sc = new Scanner(System.in);
    public void imprimir(String info){
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                                                                                                |");
        System.out.println("|                                                                                                                                |");
        System.out.println("" + info);
        System.out.println("|                                                                                                                                |");
        System.out.println("|                                                                                                                                |");
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------");
    }
    public int digitarOpcion(){
        System.out.println("\tdigite una opción para continuar");
        // Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        // sc.close();
        return opcion;
    }
    public String digitarTexto(){
        String input = sc.nextLine();
        // sc.close();
        return input;
    }
    public int MenuPrincipal(){
        imprimir("1. Entrar como admin\n2. Entrar como cliente");
        return digitarOpcion();
    }
    public void opcionInvalida(){
        imprimir("Opción inválida");
    }
}
