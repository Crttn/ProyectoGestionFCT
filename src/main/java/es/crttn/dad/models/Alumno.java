package es.crttn.dad.models;

import javafx.beans.property.*;

import java.util.Date;

public class Alumno {

    private final IntegerProperty idAlumno;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty dni;
    private final StringProperty correo;
    private final ObjectProperty<Date> fechaNacimiento;
    private final IntegerProperty telefono;
    private final IntegerProperty codigoNuss;
    private final IntegerProperty idCurso;

    public Alumno(int idAlumno, String nombre, String apellido, String dni, String correo, Date fechaNacimiento, int telefono, int codigoNuss, int idCurso) {
        this.idAlumno = new SimpleIntegerProperty(idAlumno);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.dni = new SimpleStringProperty(dni);
        this.correo = new SimpleStringProperty(correo);
        this.fechaNacimiento = new SimpleObjectProperty<>(fechaNacimiento);
        this.telefono = new SimpleIntegerProperty(telefono);
        this.codigoNuss = new SimpleIntegerProperty(codigoNuss);
        this.idCurso = new SimpleIntegerProperty(idCurso);
    }

    public IntegerProperty idAlumnoProperty() {
        return idAlumno;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public ObjectProperty<Date> fechaNacimientoProperty() {
        return fechaNacimiento;
    }

    public IntegerProperty telefonoProperty() {
        return telefono;
    }

    public IntegerProperty codigoNussProperty() {
        return codigoNuss;
    }

    public IntegerProperty idCursoProperty() {
        return idCurso;
    }

    // Getters y setters
    public int getIdAlumno() { return idAlumno.get(); }
    public String getNombre() { return nombre.get(); }
    public String getApellido() { return apellido.get(); }
    public String getDni() { return dni.get(); }
    public String getCorreo() { return correo.get(); }
    public Date getFechaNacimiento() { return fechaNacimiento.get(); }
    public int getTelefono() { return telefono.get(); }
    public int getCodigoNuss() { return codigoNuss.get(); }
    public int getIdCurso() { return idCurso.get(); }
}