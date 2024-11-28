package es.crttn.dad.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Tutor_docente {

    // Atributos como Properties
    private final IntegerProperty id_tutor_docente;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty correo;

    // Constructor
    public Tutor_docente(int id_tutor_docente, String nombre, String apellido, String correo) {
        this.id_tutor_docente = new SimpleIntegerProperty(id_tutor_docente);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
    }

    // Métodos Property
    public IntegerProperty id_tutor_docenteProperty() {
        return id_tutor_docente;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public StringProperty correoProperty() {
        return correo;
    }

    // Getters y Setters convencionales
    public int getId_tutor_docente() {
        return id_tutor_docente.get();
    }

    public void setId_tutor_docente(int id_tutor_docente) {
        this.id_tutor_docente.set(id_tutor_docente);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }
}
