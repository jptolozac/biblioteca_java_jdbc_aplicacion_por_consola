package com.acm.repository.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.acm.model.Libro;
import com.acm.repository.Conexion;
import com.acm.repository.ILibroDAO;

public class LibroDAO extends Conexion implements ILibroDAO {

    public LibroDAO() {
        super();
    }

    @Override
    public Libro get(int id) {
        String sentencia = "SELECT id, titulo, publicacion_year, disponibilidad, descripcion, id_categoria, id_autor FROM libro where id=?";
        try (PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Libro libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                libro.setCategoria(categoriaDAO.get(rs.getInt(6)));

                AutorDAO autorDAO = new AutorDAO();
                libro.setAutor(autorDAO.get(rs.getInt(7)));

                return libro;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Libro> listar() {
        List<Libro> libros = new ArrayList<>();
        String sentencia = "SELECT id, titulo, publicacion_year, disponibilidad, descripcion, id_categoria, id_autor FROM libro";
        try (PreparedStatement stmt = con.prepareStatement(sentencia)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                libro.setCategoria(categoriaDAO.get(rs.getInt(6)));

                AutorDAO autorDAO = new AutorDAO();
                libro.setAutor(autorDAO.get(rs.getInt(7)));

                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

    @Override
    public boolean agregar(Libro libro) throws SQLException {
        String sentencia = "INSERT INTO libro (titulo, descripcion, publicacion_year, disponibilidad, id_categoria, id_autor) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getDescripcion());
            stmt.setString(3, libro.getPublicacionYear());
            stmt.setInt(4, libro.getDisponibilidad());
            stmt.setInt(5, libro.getCategoria().getId());
            stmt.setInt(6, libro.getAutor().getId());
            return stmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Libro libro) {
        String sentencia = "UPDATE libro SET titulo=?, publicacion_year=?, disponibilidad=?, descripcion=?, id_categoria=?, id_autor=? WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getPublicacionYear());
            stmt.setInt(3, libro.getDisponibilidad());
            stmt.setString(4, libro.getDescripcion());
            stmt.setInt(5, libro.getCategoria().getId());
            stmt.setInt(6, libro.getAutor().getId());
            stmt.setInt(7, libro.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(int idLibro) {
        String sentencia = "DELETE FROM libro WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, idLibro);
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void crearTabla() {
        String sentencia = "CREATE TABLE IF NOT EXISTS libro (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, titulo VARCHAR(100) NOT NULL, publicacion_year VARCHAR(4), disponibilidad INT, descripcion TEXT, id_categoria INT, id_autor INT, FOREIGN KEY (id_categoria) REFERENCES categoria(id) ON DELETE CASCADE, FOREIGN KEY (id_autor) REFERENCES autor(id) ON DELETE CASCADE);";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminarTabla() {
        String sentencia = "DROP TABLE libro";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
