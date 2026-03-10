package com.alura.literatura.principal;

import com.alura.literatura.model.Datos;
import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private final String URL_BASE = "https://gutendex.com/books/";

    @Override
    public void run(String... args) {

        var opcion = -1;

        while (opcion != 0) {

            var menu = """
                    
                    -------- LITERALURA --------
                    
                    1 - Buscar libro por título
                    2 - Listar libros más descargados
                    3 - Buscar libros por idioma
                    4 - Buscar libros por autor
                    
                    0 - Salir
                    
                    ----------------------------
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    buscarLibroPorTitulo();
                    break;

                case 2:
                    librosMasDescargados();
                    break;

                case 3:
                    buscarPorIdioma();
                    break;

                case 4:
                    buscarPorAutor();
                    break;

                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private ConvierteDatos conversor = new ConvierteDatos();

    private void buscarLibroPorTitulo() {

        System.out.println("Escribe el título del libro:");
        var titulo = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(
                URL_BASE + "?search=" + titulo.replace(" ", "%20")
        );

        Datos datos = conversor.obtenerDatos(json, Datos.class);

        if (datos.results().isEmpty()) {
            System.out.println("No se encontró ningún libro con ese título.");
            return;
        }

        DatosLibro libro = datos.results().get(0);

        System.out.println("\n----- LIBRO ENCONTRADO -----");

        System.out.println("Título: " + libro.getTitle());
        System.out.println("Autor: " + libro.getAuthors().get(0).getName());
        System.out.println("Idioma: " + libro.getLanguages().get(0));
        System.out.println("Descargas: " + libro.getNumeroDescargas());
    }

    private void librosMasDescargados() {

        var json = consumoAPI.obtenerDatos(
                URL_BASE + "?sort=downloads"
        );

        Datos datos = conversor.obtenerDatos(json, Datos.class);

        System.out.println("\n----- LIBROS MÁS DESCARGADOS -----\n");

        datos.results().stream()
                .limit(5)
                .forEach(libro -> {

                    System.out.println("Título: " + libro.getTitle());
                    System.out.println("Autor: " + libro.getAuthors().get(0).getName());
                    System.out.println("Idioma: " + libro.getLanguages().get(0));
                    System.out.println("Descargas: " + libro.getNumeroDescargas());
                    System.out.println("----------------------------------");

                });
    }

    private void buscarPorIdioma() {

        System.out.println("""
            Escribe el idioma:
            es - Español
            en - Inglés
            fr - Francés
            """);

        var idioma = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(
                URL_BASE + "?languages=" + idioma
        );

        Datos datos = conversor.obtenerDatos(json, Datos.class);

        System.out.println("\n----- LIBROS ENCONTRADOS -----\n");

        datos.results().stream()
                .limit(5)
                .forEach(libro -> {

                    String autor = "Desconocido";
                    String lenguaje = "No disponible";

                    if (!libro.getAuthors().isEmpty()) {
                        autor = libro.getAuthors().get(0).getName();
                    }

                    if (!libro.getLanguages().isEmpty()) {
                        lenguaje = libro.getLanguages().get(0);
                    }

                    System.out.println("Título: " + libro.getTitle());
                    System.out.println("Autor: " + autor);
                    System.out.println("Idioma: " + lenguaje);
                    System.out.println("Descargas: " + libro.getNumeroDescargas());
                    System.out.println("----------------------------------");

                });
    }

    private void buscarPorAutor() {

        System.out.println("Escribe el nombre del autor:");
        var autor = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(
                URL_BASE + "?search=" + autor.replace(" ", "%20")
        );

        Datos datos = conversor.obtenerDatos(json, Datos.class);

        System.out.println("\n----- LIBROS DEL AUTOR -----\n");

        datos.results().stream()
                .filter(libro -> libro.getAuthors().get(0).getName().toLowerCase()
                        .contains(autor.toLowerCase()))
                .limit(5)
                .forEach(libro -> {

                    System.out.println("Título: " + libro.getTitle());
                    System.out.println("Autor: " + libro.getAuthors().get(0).getName());
                    System.out.println("Idioma: " + libro.getLanguages().get(0));
                    System.out.println("Descargas: " + libro.getNumeroDescargas());
                    System.out.println("----------------------------------");

                });
    }

}
