package com.acm.Services.Impl;

import java.sql.Date;
import java.util.List;

import com.acm.Services.ILibroService;
import com.acm.Services.IPrestamoService;
import com.acm.model.Cliente;
import com.acm.model.Libro;
import com.acm.model.Prestamo;
import com.acm.repository.IPrestamoDAO;
import com.acm.repository.IPrestamoLibroDAO;
import com.acm.repository.Impl.PrestamoDAO;
import com.acm.repository.Impl.PrestamoLibroDAO;

public class PrestamoService implements IPrestamoService{
    IPrestamoDAO prestamoDAO;
    IPrestamoLibroDAO prestamoLibroDAO;

    public PrestamoService() {
        prestamoDAO = new PrestamoDAO();
        prestamoLibroDAO = new PrestamoLibroDAO();
    }

    @Override
    public Prestamo pedir(List<Libro> libros, Cliente cliente, Date fecha_fin_prestamo) {
        Prestamo prestamo = null;
        try {
            prestamo = prestamoDAO.pedir(cliente, fecha_fin_prestamo);
            prestamoLibroDAO.registrarLibros(libros, prestamo.getId());
            for(Libro libro : libros){
                ILibroService libroService = new LibroService();
                libroService.actualizar(libro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prestamo;
    }

    @Override
    public void crearTabla() {
        prestamoDAO.crearTabla();
    }

    @Override
    public void eliminarTabla() {
        prestamoDAO.eliminarTabla();
    }
    
}
