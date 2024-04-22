package com.acm.repository;

import java.sql.SQLException;
import java.util.List;

import com.acm.model.Categoria;

public interface ICategoriaDAO {
    public void crearTabla();
    public void eliminarTabla();
    public Categoria get(int id);
    public List<Categoria> listar();
    public Categoria agregar(Categoria categoria) throws SQLException;
    public boolean actualizar(Categoria categoria);
    public boolean eliminar(Categoria categoria);
}
