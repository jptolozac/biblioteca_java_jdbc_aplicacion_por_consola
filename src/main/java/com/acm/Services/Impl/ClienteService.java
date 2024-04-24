package com.acm.Services.Impl;

import java.util.List;

import com.acm.Services.IClienteService;
import com.acm.model.Cliente;
import com.acm.repository.IClienteDAO;
import com.acm.repository.Impl.ClienteDAO;

public class ClienteService implements IClienteService{
    IClienteDAO clienteDAO;
    

    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    @Override
    public List<Cliente> listar() {
        try {
            return clienteDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean agregar(Cliente cliente) {
        if(cliente != null){
            try {
                return clienteDAO.agregar(cliente);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void crearTabla() {
        clienteDAO.crearTabla();
    }

    @Override
    public void eliminarTabla() {
        clienteDAO.eliminarTabla();
    }

    @Override
    public Cliente get(String cedula){
        try {
            if(cedula != ""){
                IClienteDAO clienteDAO = new ClienteDAO();
                return clienteDAO.get(cedula);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
