package com.acm.repository.Impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.acm.model.Cliente;
import com.acm.model.Prestamo;
import com.acm.repository.Conexion;
import com.acm.repository.IPrestamoDAO;

public class PrestamoDAO extends Conexion implements IPrestamoDAO{

    @Override
    public Prestamo pedir(Cliente cliente, Date fechaFinPrestamo) throws SQLException {
        String sentencia = "INSERT INTO prestamo (fecha_inicio_prestamo, fecha_fin_prestamo, cedula_cliente) VALUES (?,?,?)";
        Date fechaHoy = Date.valueOf(LocalDate.now());
        Prestamo prestamo = new Prestamo(cliente, fechaHoy, fechaFinPrestamo);
        try(PreparedStatement stmt = con.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, fechaHoy);
            stmt.setDate(2, fechaFinPrestamo);
            stmt.setString(3, cliente.getCedula());
            if(stmt.executeUpdate() > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    prestamo.setId(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    @Override
    public void crearTabla() {
        String sententencia = "CREATE TABLE IF NOT EXISTS prestamo (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, fecha_inicio_prestamo DATE, fecha_fin_prestamo DATE, cedula_cliente VARCHAR(15), FOREIGN KEY(cedula_cliente) REFERENCES cliente(cedula));";
        try (PreparedStatement stmt = con.prepareStatement(sententencia)) {
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTabla() {
        String sentencia = "DROP TABLE prestamo";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
