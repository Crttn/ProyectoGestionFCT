package es.crttn.dad.models;

public class Tutor_docente {

    //Atributos
    private int id_tutor_docente;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;

    //Constructor
    public Tutor_docente(int id_tutor_docente, String nombre, String apellido, String correo, String telefono) {
        this.id_tutor_docente = id_tutor_docente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    //Getters y Setters
    public int getId_tutor_docente() {
        return id_tutor_docente;
    }

    public void setId_tutor_docente(int id_tutor_docente) {
        this.id_tutor_docente = id_tutor_docente;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
