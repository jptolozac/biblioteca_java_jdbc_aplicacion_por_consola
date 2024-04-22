package com.acm.repository.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acm.model.Libro;
import com.acm.model.Prestamo;
import com.acm.repository.Conexion;
import com.acm.repository.IPrestamoLibroDAO;

public class PrestamoLibroDAO extends Conexion implements IPrestamoLibroDAO{
    public PrestamoLibroDAO(){
        super();
    }

    @Override
    public void crearTabla() {
        String sentencia = "CREATE TABLE IF NOT EXISTS prestamo_libro (id_libro INT NOT NULL, id_prestamo INT NOT NULL, PRIMARY KEY(id_libro, id_prestamo), FOREIGN KEY(id_libro) REFERENCES libro(id), FOREIGN KEY(id_prestamo) REFERENCES prestamo(id));";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTabla() {
        String sentencia = "DROP TABLE prestamo_libro";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> getLibrosPrestados(int idPrestamo) {
        List<Libro> libros = new ArrayList<>();
        String sentencia = "SELECT lb.* FROM prestamo_libro pl JOIN prestamo pr on (pl.id_prestamo = pr.id) JOIN libro lb on (pl.id_libro = lb.id) where pr.id = ?;";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                libros.add(new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public List<Prestamo> getPrestamosPorLibro(int idLibro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrestamosPorLibro'");
    }

    @Override
    public boolean registrarLibros(List<Libro> libros, int idPrestamo) {
        boolean exito = false;
        try {
            for(Libro libro : libros){
                String sentencia = "INSERT INTO prestamo_libro (id_libro, id_prestamo) VALUES (?,?)";
                PreparedStatement stmt = con.prepareStatement(sentencia);
                stmt.setInt(1, libro.getId());
                stmt.setInt(2, idPrestamo);
                exito = stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
    
}
