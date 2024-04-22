package com.acm.Services;

import com.acm.model.Cliente;
import com.acm.repository.IClienteDAO;
import com.acm.repository.Impl.ClienteDAO;

import java.util.List;

public interface IClienteService {
    public List<Cliente> listar();
    public static Cliente get(String cedula){
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
    public boolean agregar(Cliente cliente);
    public void crearTabla();
    public void eliminarTabla();
}
