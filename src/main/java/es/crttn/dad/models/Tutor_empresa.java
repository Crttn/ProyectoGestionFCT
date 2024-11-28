package es.crttn.dad.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Tutor_empresa {

    // Atributos como Properties
    private final IntegerProperty id_tutor_empresa;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty correo;
    private final IntegerProperty telefono;

    // Constructor
    public Tutor_empresa(int id_tutor_empresa, String nombre, String apellido, String correo, int telefono) {
        this.id_tutor_empresa = new SimpleIntegerProperty(id_tutor_empresa);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
        this.telefono = new SimpleIntegerProperty(telefono);
    }

    // Métodos Property
    public IntegerProperty id_tutor_empresaProperty() {
        return id_tutor_empresa;
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

    public IntegerProperty telefonoProperty() {
        return telefono;
    }

    // Getters y Setters convencionales
    public int getId_tutor_empresa() {
        return id_tutor_empresa.get();
    }

    public void setId_tutor_empresa(int id_tutor_empresa) {
        this.id_tutor_empresa.set(id_tutor_empresa);
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

    public int getTelefono() {
        return telefono.get();
    }

    public void setTelefono(int telefono) {
        this.telefono.set(telefono);
    }
}
