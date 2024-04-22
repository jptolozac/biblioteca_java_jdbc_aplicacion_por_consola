package com.acm.Services.Impl;

import java.sql.SQLException;
import java.util.List;

import com.acm.Services.ICategoriaService;
import com.acm.model.Categoria;
import com.acm.repository.Impl.CategoriaDAO;

public class CategoriaService implements ICategoriaService{
    CategoriaDAO categoriaDAO;
    

    public CategoriaService() {
        categoriaDAO = new CategoriaDAO();
    }

    @Override
    public Categoria get(int id) {
        if(id > 0){
            return categoriaDAO.get(id);
        }
        return null;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }

    @Override
    public Categoria agregar(Categoria categoria) {
        if(categoria != null){
            try {
                return categoriaDAO.agregar(categoria);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Categoria categoria) {
        if(categoria != null)
            return categoriaDAO.actualizar(categoria);
        return false;
    }

    @Override
    public boolean eliminar(Categoria categoria) {
        if(categoria.getId() > 0)
            return categoriaDAO.eliminar(categoria);
        return false;
    }

    @Override
    public void crearTabla() {
        categoriaDAO.crearTabla();
    }

    @Override
    public void eliminarTabla() {
        categoriaDAO.eliminarTabla();
    }
    
}
