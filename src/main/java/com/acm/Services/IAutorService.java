package com.acm.Services;

import com.acm.model.Autor;
import java.util.List;

public interface IAutorService {
    public Autor get(int id);
    public List<Autor> listar();
    public Autor agregar(Autor Autor);
    public boolean actualizar(Autor Autor);
    public boolean eliminar(Autor Autor);
    public void crearTabla();
    public void eliminarTabla();
}