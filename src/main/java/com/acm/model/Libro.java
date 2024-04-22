package com.acm.model;

import java.util.List;

public class Libro {
    int id;
    String titulo;
    String publicacionYear;
    int disponibilidad;
    String descripcion;
    Categoria categoria;
    Autor autor;
    List<Prestamo> prestamos;
    public Libro(int id, String titulo, String publicacionYear, int disponibilidad, String descripcion,
            Categoria categoria, Autor autor, List<Prestamo> prestamos) {
        this.id = id;
        this.titulo = titulo;
        this.publicacionYear = publicacionYear;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.autor = autor;
        this.prestamos = prestamos;
    }
    
    public Libro(String titulo, String publicacionYear, int disponibilidad, String descripcion, Categoria categoria,
            Autor autor, List<Prestamo> prestamos) {
        this.titulo = titulo;
        this.publicacionYear = publicacionYear;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.autor = autor;
        this.prestamos = prestamos;
    }
    

    public Libro(int id, String titulo, String publicacionYear, int disponibilidad, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.publicacionYear = publicacionYear;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
    }
    

    public Libro(String titulo, String publicacionYear, int disponibilidad, String descripcion) {
        this.titulo = titulo;
        this.publicacionYear = publicacionYear;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getPublicacionYear() {
        return publicacionYear;
    }
    public void setPublicacionYear(String publicacionYear) {
        this.publicacionYear = publicacionYear;
    }
    public int getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return id + ". " + titulo + ": " + descripcion + "\tCategoria: " + categoria;
    }

    public String toStringDisponibilidad(){
        return titulo + ": " + descripcion + "\tCategoria: " + categoria.getNombre() + "\tDisponibilidad: " + disponibilidad + '\n';
    }

    public String toStringAll() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", publicacionYear=" + publicacionYear + ", disponibilidad="
                + disponibilidad + ", descripcion=" + descripcion + ", categoria=" + categoria + ", autor=" + autor
                + ", prestamos=" + prestamos + "]";
    }

    
    
}
