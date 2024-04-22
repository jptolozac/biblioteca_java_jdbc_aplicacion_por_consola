package com.acm.repository;

import java.sql.SQLException;
import java.util.List;

import com.acm.model.Autor;

public interface IAutorDAO {
    public void crearTabla();
    public void eliminarTabla();
    public Autor get(int id);
    public List<Autor> listar();
    public Autor agregar(Autor autor) throws SQLException;
    public boolean actualizar(Autor autor);
    public boolean eliminar(Autor autor);
}