package comiccollector.app;

import comiccollector.colecciones.ComicCollector;
import comiccollector.modelo.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

//Clase principal que funciona con el comicCollector

public class Main {
    public static void main(String[] args) {
        ComicCollector sistema = new ComicCollector();
        Scanner sc = new Scanner(System.in);

        // cargar el catálogo inicial de cómics (ya subido en un archivo csv con titulos random)
        sistema.cargarComicsDesdeCSV("comics.csv");

        int opcion;
        do {
            System.out.println("\n=== ComicCollector ===");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Reservar cómic");
            System.out.println("3. Listar cómics");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Guardar usuarios");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("RUT (XX.XXX.XXX-X): ");
                        String rut = sc.nextLine();
                        try {
                            Usuario usuario = new Usuario(nombre, rut);
                            sistema.registrarUsuario(usuario);
                            System.out.println("Usuario registrado.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.print("RUT del usuario: ");
                        String rut = sc.nextLine();
                        System.out.print("Título del cómic: ");
                        String titulo = sc.nextLine();
                        sistema.reservarComic(rut, titulo);
                    }
                    case 3 -> sistema.getCatalogo().forEach(System.out::println);
                    case 4 -> sistema.guardarUsuariosEnArchivo("usuarios.txt");
                    case 5 -> sistema.guardarUsuariosEnArchivo("usuarios.txt");
                    case 6 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
                opcion = 0;
            }

        } while (opcion != 6);

        sc.close();
    }
}
