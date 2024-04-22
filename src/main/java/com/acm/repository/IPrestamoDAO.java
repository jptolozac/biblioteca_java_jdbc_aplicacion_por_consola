package com.acm.repository;

import java.sql.Date;
import java.sql.SQLException;

import com.acm.model.Cliente;
import com.acm.model.Prestamo;

public interface IPrestamoDAO {
    public Prestamo pedir(Cliente cliente, Date fechaFinPrestamo) throws SQLException;
    public void crearTabla();
    public void eliminarTabla();
}
