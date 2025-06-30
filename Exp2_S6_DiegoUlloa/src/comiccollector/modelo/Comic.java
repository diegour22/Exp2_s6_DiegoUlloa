package comiccollector.modelo;

/**
 * Clase que representa un cómic en la colección.
 * Contiene título, editorial, precio y disponibilidad.
 */
public class Comic {
    private String titulo;
    private String editorial;
    private double precio;
    private boolean disponible;

    /**
     * Constructor del cómic
     * @param titulo título del cómic
     * @param editorial editorial que publica el cómic
     * @param precio precio del cómic
     */
    public Comic(String titulo, String editorial, double precio) {
        this.titulo = titulo;
        this.editorial = editorial;
        this.precio = precio;
        this.disponible = true; // por defecto disponible
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void marcarComoNoDisponible() {
        this.disponible = false;
    }

    public void marcarComoDisponible() {
        this.disponible = true;
    }

    @Override
    public String toString() {
        return "Comic: " + titulo + " | Editorial: " + editorial + " | Precio: $" + precio
                + " | Disponible: " + (disponible ? "Sí" : "No");
    }
}
