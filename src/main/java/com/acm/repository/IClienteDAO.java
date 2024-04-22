package com.acm.repository;

import java.sql.SQLException;
import java.util.List;

import com.acm.model.Cliente;

public interface IClienteDAO {
    public List<Cliente> listar() throws SQLException;
    public  Cliente get(String cedula) throws SQLException;
    public boolean agregar(Cliente cliente) throws SQLException;
    public void crearTabla();
    public void eliminarTabla();
}
