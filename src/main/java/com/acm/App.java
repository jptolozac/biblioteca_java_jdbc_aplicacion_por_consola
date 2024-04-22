package com.acm;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.acm.Services.IAutorService;
import com.acm.Services.ICategoriaService;
import com.acm.Services.IClienteService;
import com.acm.Services.ILibroService;
import com.acm.Services.IPrestamoService;
import com.acm.Services.LoginService;
import com.acm.Services.Impl.AutorService;
import com.acm.Services.Impl.CategoriaService;
import com.acm.Services.Impl.LibroService;
import com.acm.Services.Impl.PrestamoService;
import com.acm.model.Autor;
import com.acm.model.Categoria;
import com.acm.model.Cliente;
import com.acm.model.Libro;
import com.acm.views.VistaAdmin;
import com.acm.views.VistaCliente;
import com.acm.views.VistaLibro;
import com.acm.views.VistaUsuario;

public class App {
    public static void cargaLibros() {
        ILibroService libroService = new LibroService();
        VistaLibro vwLibro = new VistaLibro();

        do{
            Libro libro = editarLibro();
            try {
                libroService.agregar(libro);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }while (vwLibro.agregarLibro());
    }

    public static Libro editarLibro() {
        IAutorService autorService = new AutorService();
        ICategoriaService categoriaService = new CategoriaService();

        VistaLibro vwLibro = new VistaLibro();

        String imprimirAutores = "Seleccione el autor:\n";
        String imprimirCategorias = "Seleccione la categoria:\n";

        List<Autor> autores = autorService.listar();
        for (Autor autor : autores) {
            imprimirAutores += autor.toString();
        }
        int opcCrear = (autores.get(autores.size() - 1).getId() + 1);
        imprimirAutores += opcCrear + ". crear nuevo autor";
        int opcion = 0;
        Autor autor = null;
        while (opcion > opcCrear || opcion < 1) {
            vwLibro.imprimir(imprimirAutores);
            opcion = vwLibro.digitarOpcion();
            if (opcion == opcCrear) {
                autor = new Autor(vwLibro.ObtenerAtributo("nombre del autor"),
                        vwLibro.ObtenerAtributo("país de origen del autor"));
                autorService.agregar(autor);
            } else if (opcion > opcCrear || opcion < 1) {
                vwLibro.imprimir("Digite una opción válida");
                continue;
            } else {
                autor = autorService.get(opcion);
            }
        }

        List<Categoria> categorias = categoriaService.listar();
        for (Categoria categoria : categorias) {
            imprimirCategorias += categoria.toString();
        }
        opcCrear = (categorias.get(categorias.size() - 1).getId() + 1);
        imprimirCategorias += opcCrear + ". crear nueva categoria";
        opcion = 0;
        Categoria categoria = null;
        while (opcion > opcCrear || opcion < 1) {
            vwLibro.imprimir(imprimirCategorias);
            opcion = vwLibro.digitarOpcion();
            if (opcion == opcCrear) {
                categoria = new Categoria(vwLibro.ObtenerAtributo("nombre de la categoría"),
                        vwLibro.ObtenerAtributo("descripción de la categoría"));
                categoriaService.agregar(categoria);
            } else if (opcion > opcCrear || opcion < 1) {
                vwLibro.imprimir("Digite una opción válida");
                continue;
            } else {
                categoria = categoriaService.get(opcion);
            }
        }

        return new Libro(vwLibro.ObtenerAtributo("título del libro"), vwLibro.ObtenerAtributo("año de publicación"),
                Integer.parseInt(vwLibro.ObtenerAtributo("disponibilidad de libros")),
                vwLibro.ObtenerAtributo("descripción del libro"), categoria, autor, null);
    }

    public static void start() {
        LoginService loginService = new LoginService();
        int ingresa = 0;
        do {
            VistaUsuario vwUsuario = new VistaUsuario();
            ingresa = loginService.ingresar(vwUsuario.ingresar(), vwUsuario.getNombreUsuario());
            System.out.println("ingresa: " + ingresa);
        } while (ingresa != loginService.ADMIN && ingresa != loginService.CLIENTE);

        if (ingresa == loginService.ADMIN) {
            VistaAdmin vwUsuario = new VistaAdmin();
            ILibroService libroService = new LibroService();
            switch (vwUsuario.Menu()) {
                case 1: {
                    cargaLibros();
                }
                    break;
                case 2: {
                    List<Libro> libros = libroService.listar();
                    String salida = "Elija el libro a editar\n";
                    for (Libro libro : libros) {
                        salida += libro.toString();
                    }
                    vwUsuario.imprimir(salida);
                    Libro libroSinEditar = libroService.get(vwUsuario.digitarOpcion());
                    if (libroSinEditar != null) {
                        Libro libroEditado = editarLibro();
                        libroEditado.setId(libroSinEditar.getId());
                        libroService.actualizar(libroEditado);
                        VistaLibro.exito();
                    } else {
                        VistaLibro.libroSinResultados();
                    }
                }
                    break;
                case 3: {
                    List<Libro> libros = libroService.listar();
                    String salida = "Elija el libro a eliminar\n";
                    for (Libro libro : libros) {
                        salida += libro.toString();
                    }
                    vwUsuario.imprimir(salida);
                    libroService.eliminar(vwUsuario.digitarOpcion(), libros.get(libros.size() - 1).getId());
                }
                    break;
                default:
                    vwUsuario.opcionInvalida();
                    break;
            }

        } else if(ingresa == loginService.CLIENTE) {
            VistaCliente vwUsuario = new VistaCliente();
            Cliente cliente = IClienteService.get(loginService.getUsername());
            System.out.println("Cliente: " + cliente);
            System.out.println("Username: " + loginService.getUsername());
            ILibroService libroService = new LibroService();
            switch (vwUsuario.Menu()) {
                case 1: {
                    List<Libro> libros = libroService.listar();
                    String imprimir = "";
                    for (int i = 0; i < libros.size(); i++) {
                        imprimir += i + ". " + libros.get(i).toStringDisponibilidad();
                    }
                    List<Libro> librosElegidos = new ArrayList<>();
                    LocalDate fechaFinPrestamo = LocalDate.now().plusDays(vwUsuario.diasPrestamo());
                    do {
                        vwUsuario.imprimir(imprimir);
                        int eleccion = vwUsuario.digitarOpcion();
                        Libro libroElegido = libros.get(eleccion);
                        if (libroElegido.getDisponibilidad() > 0) {
                            boolean repetido = false;
                            for(Libro libro : librosElegidos){
                                if(libro.equals(libroElegido)){
                                    VistaLibro.libroRepetido();
                                    repetido = true;
                                }
                            }
                            if(repetido)
                                continue;

                            libroElegido.setDisponibilidad(libroElegido.getDisponibilidad() - 1);
                            
                            librosElegidos.add(libroElegido);
                        }
                    } while (vwUsuario.pedirOtroLibro());
                    IPrestamoService prestamo = new PrestamoService();
                    prestamo.pedir(librosElegidos, cliente, Date.valueOf(fechaFinPrestamo));
                }
                    break;
                default:
                    vwUsuario.imprimir("Opción inválida");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        VistaUsuario vwUsuario = new VistaUsuario();
        if (LoginService.validarExistenciaLibros()) {
            if (!vwUsuario.continuar()) {
                cargaLibros();
            }
        } else {
            cargaLibros();
        }

        start();
    }
}
