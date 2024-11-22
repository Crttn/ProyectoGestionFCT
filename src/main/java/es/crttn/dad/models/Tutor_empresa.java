package es.crttn.dad.models;

public class Tutor_empresa {

    //Atributos
    private int id_tutor_empresa;
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;

    // Constructor
    public Tutor_empresa(int id_tutor_empresa, String nombre, String apellido, String correo, int telefono) {
        this.id_tutor_empresa = id_tutor_empresa;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getId_tutor_empresa() {
        return id_tutor_empresa;
    }

    public void setId_tutor_empresa(int id_tutor_empresa) {
        this.id_tutor_empresa = id_tutor_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}