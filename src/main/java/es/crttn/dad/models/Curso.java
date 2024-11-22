package es.crttn.dad.models;

public class Curso {

    // Atributos
    private int id_curso;
    private String nombre;
    private String descripcion;

    // Constructor
    public Curso() {
    }

    public Curso(int id_curso, String nombre, String descripcion) {
        this.id_curso = id_curso;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id_curso=" + id_curso +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}