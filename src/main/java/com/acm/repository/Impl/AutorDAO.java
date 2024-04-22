package com.acm.repository.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.acm.model.Autor;
import com.acm.repository.Conexion;
import com.acm.repository.IAutorDAO;

public class AutorDAO extends Conexion implements IAutorDAO{

    public AutorDAO(){
        super();
    }

    @Override
    public void crearTabla(){
        String sentencia = "CREATE TABLE IF NOT EXISTS autor (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), pais_origen VARCHAR(50));";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void eliminarTabla(){
        String sentencia = "DROP TABLE autor";
        try(Statement stmt = con.createStatement()){
            stmt.execute(sentencia);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Autor get(int id) {
        String sentencia = "SELECT * FROM autor WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Autor(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Autor> listar() {
        String sentencia = "SELECT * FROM autor";
        List<Autor> autores = new ArrayList<>();
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                autores.add(new Autor(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return autores;
    }
    @Override
    public Autor agregar(Autor autor) throws SQLException {
        String sentencia = "INSERT INTO autor (nombre, pais_origen) values (?,?)";
        try(PreparedStatement stmt = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getPaisOrigen());
            if(stmt.executeUpdate() > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    autor.setId((int)rs.getLong(1));
                }
            }
        }catch(Exception e){
            System.out.println("Error: " + e);
        }

        return autor;
    }

    @Override
    public boolean actualizar(Autor autor) {
        String sentencia = "UPDATE autor SET nombre=?, pais_origen=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getPaisOrigen());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean eliminar(Autor autor) {
        String sentencia = "DELETE FROM autor WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, autor.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
