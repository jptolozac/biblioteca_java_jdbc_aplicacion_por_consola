package com.acm.repository;

import java.sql.SQLException;
import java.util.List;

import com.acm.model.Libro;

public interface ILibroDAO {
    public Libro get(int id);
    public List<Libro> listar();
    public boolean agregar(Libro libro) throws SQLException;
    public boolean actualizar(Libro libro);
    public boolean eliminar(int idLibro);
    public void crearTabla();
    public void eliminarTabla();
}
