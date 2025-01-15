package com.alura.literAlura.principal;

import com.alura.literAlura.dto.LibroDTO;
import com.alura.literAlura.model.Autores;
import com.alura.literAlura.model.DatosLibro;
import com.alura.literAlura.model.Libro;
import com.alura.literAlura.repository.AutorRepository;
import com.alura.literAlura.repository.LibroRepository;
import com.alura.literAlura.service.ConsumoAPI;
import com.alura.literAlura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.repositorio = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    librosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void librosPorIdioma() {
        List<Libro> libros = null;
        boolean idiomaValido = false;

        while (!idiomaValido) {
            System.out.println("Elige un idioma: \n" +
                    "es - Español\n" +
                    "en - Inglés\n" +
                    "fr - Francés\n" +
                    "de - Alemán\n" +
                    "pt - Portugués\n");

            var idioma = teclado.nextLine();

            switch (idioma) {
                case "es":
                case "en":
                case "fr":
                case "de":
                case "pt":
                    libros = repositorio.buscarPorIdioma(idioma);
                    idiomaValido = true;
                    break;
                default:
                    System.out.println("Idioma no válido. Por favor, ingresa las siglas correctas.\n");
                    break;
            }
        }
        // Mostrar los libros encontrados
        System.out.println("Libros encontrados: \n");
        libros.forEach(l -> System.out.println("---------- Libro --------------\n" +
                "Título: " + l.getTitulo() + "\n" +
                "Idioma: " + l.getIdiomas() + "\n" +
                "Número de descargas: " + l.getNumeroDescargas() + "\n" +
                "Autor: " + l.getAutor().getNombre() +
                "\n---------------------" + "\n"));
    }

    private void autoresVivos() {
        System.out.println("Indica el año:");
        var anio = teclado.nextInt();
        List<Autores> autores = autorRepository.autoresPorAnio(anio);
        System.out.println("Autores encontrados: \n ");
        autores.forEach(System.out::println);
    }

    private void autoresRegistrados() {
        List<Autores> autores = autorRepository.findAll();
        System.out.println("Autores encontrados:\n ");
        autores.forEach(System.out::println);
    }

    private void librosRegistrados() {
        List<Libro> libros = repositorio.findAll();
        System.out.println("Libros encontrados:");
        libros.forEach(l -> System.out.println("---------- Libro --------------\n" +
                "Título: " + l.getTitulo() + "\n" +
                "Idioma: " + l.getIdiomas() + "\n" +
                "Número de descargas: " + l.getNumeroDescargas() + "\n" +
                "Autor: " + l.getAutor().getNombre() +
                "\n---------------------" + "\n"));

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    private void buscarLibro() {
        boolean exito = false;
        while (!exito) {
            try {
                DatosLibro datos = getDatosLibro();
                // Verificar si el autor ya existe en la base de datos
                Optional<Autores> autorExistente = autorRepository.findByNombreIgnoreCase(datos.autor().get(0).nombre());
                Autores autor;
                if (autorExistente.isPresent()) {
                    autor = autorExistente.get(); // Usar el autor existente
                } else {
                    autor = new Autores(datos.autor().get(0)); // Crear nuevo autor si no existe
                    autor = autorRepository.save(autor); // Guardar el nuevo autor para que esté gestionado
                }
                // Crea el objeto Libro
                Libro libro = new Libro(datos);
                // Asociar el autor al libro
                libro.setAutor(autor);
                autor.getLibros().add(libro); // Agrega el libro a la lista del autor

                // Intenta guardar en el repositorio
                repositorio.save(libro);

                System.out.println("Libro guardado exitosamente: \n" + libro);
                exito = true; // Sal del ciclo si se guarda correctamente

            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                System.out.println("Error: Ya existe un libro con el mismo título. Intenta con otro libro.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }

}
