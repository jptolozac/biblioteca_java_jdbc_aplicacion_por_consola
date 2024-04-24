package com.acm.Services;

import com.acm.model.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> listar();
    public Cliente get(String cedula);
    public boolean agregar(Cliente cliente);
    public void crearTabla();
    public void eliminarTabla();
}
