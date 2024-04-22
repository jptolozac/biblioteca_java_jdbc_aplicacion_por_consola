package com.acm.Services;

import java.sql.SQLException;

import com.acm.model.Cliente;
import com.acm.repository.IClienteDAO;
import com.acm.repository.ILibroDAO;
import com.acm.repository.Impl.ClienteDAO;
import com.acm.repository.Impl.LibroDAO;

public class LoginService {
    private String ADMINUSERNAME = "1234";
    private String username;
    public int ADMIN = 1;
    public int CLIENTE = 2;

    
    public int ingresar(int opc, String username){
        // VistaUsuario usuario = null;
        if(opc == 1){
            if(ADMINUSERNAME.equals(username)){
                return ADMIN;
            } 
        }
        if(opc == 2){
            try {
                IClienteDAO clienteDAO = new ClienteDAO();
                Cliente clienteBuscado = clienteDAO.get(username);
                if(clienteBuscado != null){
                    this.username = username;
                    return CLIENTE;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public static boolean validarExistenciaLibros(){
        ILibroDAO libroDAO = new LibroDAO();
        return libroDAO.listar().size() > 0;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
