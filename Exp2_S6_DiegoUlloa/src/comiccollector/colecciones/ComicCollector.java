package comiccollector.colecciones;

import comiccollector.modelo.Comic;
import comiccollector.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que gestiona la colección de cómics y usuarios
 * Permite leer cómics desde archivo CSV y guardar usuarios en archivo TXT
 */
public class ComicCollector {
    private List<Comic> catalogo;
    private HashMap<String, Usuario> usuarios;

    public ComicCollector() {
        catalogo = new ArrayList<>();
        usuarios = new HashMap<>();
    }

    /**
     * Carga los cómics desde un archivo CSV

     */
    public void cargarComicsDesdeCSV(String archivoRuta) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoRuta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String titulo = partes[0].trim();
                    String editorial = partes[1].trim();
                    double precio = Double.parseDouble(partes[2].trim());
                    Comic comic = new Comic(titulo, editorial, precio);
                    catalogo.add(comic);
                }
            }
            System.out.println("Cómics cargados correctamente.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar cómics: " + e.getMessage());
        }
    }

    /**
     * Guarda la información de los usuarios en un archivo TXT

     */
    public void guardarUsuariosEnArchivo(String archivoRuta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoRuta))) {
            for (Usuario usuario : usuarios.values()) {
                bw.write(usuario.toString());
                bw.newLine();
            }
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getRut(), usuario);
    }

    public Usuario buscarUsuario(String rut) {
        return usuarios.get(rut);
    }

    public List<Comic> getCatalogo() {
        return catalogo;
    }

    /**
     * Marca un cómic como reservado por el usuario y lo agrega a su lista
     * @param rut rut del usuario
     * @param tituloComic título del cómic
     * @return true si la operación fue exitosa
     */
    public boolean reservarComic(String rut, String tituloComic) {
        Usuario usuario = usuarios.get(rut);
        if (usuario == null) {
            System.out.println("Usuario no registrado.");
            return false;
        }

        for (Comic comic : catalogo) {
            if (comic.getTitulo().equalsIgnoreCase(tituloComic) && comic.isDisponible()) {
                comic.marcarComoNoDisponible();
                usuario.agregarComic(comic);
                System.out.println("Comic reservado correctamente.");
                return true;
            }
        }

        System.out.println("Comic no disponible o no encontrado.");
        return false;
    }
}
