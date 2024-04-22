package com.acm.repository.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.acm.model.Categoria;
import com.acm.repository.Conexion;
import com.acm.repository.ICategoriaDAO;

public class CategoriaDAO extends Conexion implements ICategoriaDAO{
    public CategoriaDAO(){
        super();
    }

    @Override
    public void crearTabla() {
        String sentencia = "CREATE TABLE IF NOT EXISTS categoria (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), descripcion TEXT);";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminarTabla() {
        String sentencia = "DROP TABLE categoria";
        try(Statement stmt = con.createStatement()){
            stmt.execute(sentencia);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Categoria get(int id) {
        String sentencia = "SELECT * FROM categoria WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Categoria> listar() {
        String sentencia = "SELECT * FROM categoria";
        List<Categoria> categorias = new ArrayList<>();
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return categorias;
    }

    @Override
    public Categoria agregar(Categoria categoria) throws SQLException {
        String sentencia = "INSERT INTO categoria (nombre, descripcion) values (?,?)";
        try(PreparedStatement stmt = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            if(stmt.executeUpdate() > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    categoria.setId(rs.getInt(1));
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return categoria;
    }

    @Override
    public boolean actualizar(Categoria categoria) {
        String sentencia = "UPDATE categoria SET nombre=?, descripcion=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(Categoria categoria) {
        String sentencia = "DELETE FROM categoria WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, categoria.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
