package com.acm.Services;

import java.util.List;

import com.acm.model.Categoria;

public interface ICategoriaService {
    public Categoria get(int id);
    public List<Categoria> listar();
    public Categoria agregar(Categoria categoria);
    public boolean actualizar(Categoria Categoria);
    public boolean eliminar(Categoria Categoria);
    public void crearTabla();
    public void eliminarTabla();
}
