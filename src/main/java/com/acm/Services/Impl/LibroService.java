package com.acm.Services.Impl;

import java.sql.SQLException;
import java.util.List;

import com.acm.Services.IAutorService;
import com.acm.Services.ICategoriaService;
import com.acm.Services.ILibroService;
import com.acm.model.Libro;
import com.acm.repository.ILibroDAO;
import com.acm.repository.Impl.LibroDAO;

public class LibroService implements ILibroService{
    ILibroDAO libroDAO;
    IAutorService autorService = new AutorService();
    ICategoriaService categoriaService = new CategoriaService();

    public LibroService() {
        libroDAO = new LibroDAO();
    }

    @Override
    public Libro get(int id) {
        if(id > 0){
            return libroDAO.get(id);
        }
        return null;
    }

    @Override
    public List<Libro> listar() {
        return libroDAO.listar();
    }

    @Override
    public boolean agregar(Libro libro) throws SQLException {
        if(libro != null && libro.getCategoria() != null && libro.getAutor() != null){
            try {
                autorService.agregar(libro.getAutor());
                categoriaService.agregar(libro.getCategoria());
                libroDAO.agregar(libro);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean actualizar(Libro libro) {
        if(libro != null){
            return libroDAO.actualizar(libro);
        }
        return false;
    }

    @Override
    public boolean eliminar(int idLibro, int idMax) {
        if(idLibro != 0 && idLibro <= idMax){
            return libroDAO.eliminar(idLibro);
        }
        return false;
    }

    @Override
    public void crearTabla() {
        autorService.crearTabla();
        categoriaService.crearTabla();
        libroDAO.crearTabla();
    }

    @Override
    public void eliminarTabla() {
        autorService.eliminarTabla();
        categoriaService.eliminarTabla();
        libroDAO.eliminarTabla();
    }
    
}
