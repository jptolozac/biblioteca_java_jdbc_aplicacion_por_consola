package com.acm.repository.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acm.model.Cliente;
import com.acm.repository.Conexion;
import com.acm.repository.IClienteDAO;

public class ClienteDAO extends Conexion implements IClienteDAO {
    public ClienteDAO(){
        super();
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        String sentencia = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public boolean agregar(Cliente cliente) throws SQLException {
        String sentencia = "INSERT INTO cliente (cedula, nombre, correo, telefono, estado_cuenta) values (?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sentencia);
            stmt.setString(1, cliente.getCedula());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getEstadoDeCuenta());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Cliente get(String id) throws SQLException {
        String sentencia = "SELECT * FROM cliente WHERE cedula=?;";
        Conexion con = new Conexion();
        try(PreparedStatement stmt = con.getConexion().prepareStatement(sentencia)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @Override
    public void crearTabla() {
        String sentencia = "CREATE TABLE IF NOT EXISTS cliente (cedula VARCHAR(11) NOT NULL PRIMARY KEY, nombre VARCHAR(50), correo VARCHAR(60), telefono VARCHAR(15), estado_cuenta DOUBLE DEFAULT 0)";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTabla() {
        String sentencia = "DROP TABLE cliente";
        try {
            PreparedStatement stmt = con.prepareStatement(sentencia);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
