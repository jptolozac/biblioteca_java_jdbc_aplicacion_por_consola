package com.acm.repository;

import java.util.List;

import com.acm.model.Libro;
import com.acm.model.Prestamo;

public interface IPrestamoLibroDAO {
    public void crearTabla();
    public void eliminarTabla();
    public List<Libro> getLibrosPrestados(int idPrestamo);
    public List<Prestamo> getPrestamosPorLibro(int idLibro);
    public boolean registrarLibros(List<Libro> libros, int idPrestamo);
}