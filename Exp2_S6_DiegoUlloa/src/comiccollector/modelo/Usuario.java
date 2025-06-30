package comiccollector.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario del sistema ComicCollector.
 * Permite gestionar los cómics comprados o reservados.
 */
public class Usuario {
    private String nombre;
    private String rut;
    private List<Comic> comicsComprados;

    /**
     * Constructor del usuario
     * @param nombre nombre del usuario
     * @param rut rut chileno en formato XX.XXX.XXX-X
     */
    public Usuario(String nombre, String rut) {
        if (!rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]")) {
            throw new IllegalArgumentException("Formato de RUT inválido.");
        }
        this.nombre = nombre;
        this.rut = rut;
        this.comicsComprados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public List<Comic> getComicsComprados() {
        return comicsComprados;
    }

    /**
     * Permite agregar un cómic a la lista de compras del usuario
     * @param comic el cómic que se agrega
     */
    public void agregarComic(Comic comic) {
        comicsComprados.add(comic);
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " | RUT: " + rut + " | Cómics comprados: " + comicsComprados.size();
    }
}
