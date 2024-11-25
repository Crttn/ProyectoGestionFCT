package es.crttn.dad.models;

import java.util.Date;

public class Alumno {

    // Atributos
    private int idAlumno;
    private String nombre;
    private String apellido;
    private String correo;
    private String dni;
    private Date fechaNacimiento;
    private String telefono;
    private int codigoNuss;
    private int curso;

    // Constructor
    public Alumno(int idAlumno, String nombre, String apellido, String correo, String dni, Date fechaNacimiento, String telefono, int codigoNuss, int curso) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.codigoNuss = codigoNuss;
        this.curso = curso;
    }

    // Getters y Setters
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCodigoNuss() {
        return codigoNuss;
    }

    public void setCodigoNuss(int codigoNuss ) {
        this.codigoNuss = codigoNuss;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
}