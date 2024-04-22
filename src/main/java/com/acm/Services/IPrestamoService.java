package com.acm.Services;

import java.sql.Date;
import java.util.List;

import com.acm.model.Cliente;
import com.acm.model.Libro;
import com.acm.model.Prestamo;

public interface IPrestamoService {
    public Prestamo pedir(List<Libro> libros, Cliente cliente, Date fecha_fin_prestamo);
    public void crearTabla();
    public void eliminarTabla();
}
