package com.acm.Services.Impl;

import java.util.List;

import com.acm.Services.IAutorService;
import com.acm.model.Autor;
import com.acm.repository.Impl.AutorDAO;

public class AutorService implements IAutorService {
    AutorDAO autorDAO;
    
    public AutorService() {
        autorDAO = new AutorDAO();
    }

    @Override
    public Autor get(int id) {
        if(id > 0){
            return autorDAO.get(id);
        }
        return null;
    }

    @Override
    public List<Autor> listar() {
        return autorDAO.listar();
    }

    @Override
    public Autor agregar(Autor autor) {
        if(autor != null){
            try {
                return autorDAO.agregar(autor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Autor autor) {
        if(autor != null){
            return autorDAO.eliminar(autor);
        }
        return false;
    }

    @Override
    public boolean eliminar(Autor autor) {
        if(autor != null){
            return autorDAO.eliminar(autor);
        }
        return false;
    }

    @Override
    public void crearTabla() {
        autorDAO.crearTabla();
    }

    @Override
    public void eliminarTabla() {
        autorDAO.eliminarTabla();
    }
    
}
