package es.crttn.dad.models;

import java.util.Date;

public class Practicas {

    // Attributes
    private int id_practica;
    private int id_alumno;
    private int id_empresa;
    private int id_tutor_empresa;
    private int id_tutor_docente;
    private Date fecha_inicio;
    private Date fecha_fin;

    // Constructor
    public Practicas(int id_practica, int id_alumno, int id_empresa, int id_tutor_empresa, int id_tutor_docente, Date fecha_inicio, Date fecha_fin) {
        this.id_practica = id_practica;
        this.id_alumno = id_alumno;
        this.id_empresa = id_empresa;
        this.id_tutor_empresa = id_tutor_empresa;
        this.id_tutor_docente = id_tutor_docente;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    // Getters y Setters
    public int getId_practica() {
        return id_practica;
    }

    public void setId_practica(int id_practica) {
        this.id_practica = id_practica;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_tutor_empresa() {
        return id_tutor_empresa;
    }

    public void setId_tutor_empresa(int id_tutor_empresa) {
        this.id_tutor_empresa = id_tutor_empresa;
    }

    public int getId_tutor_docente() {
        return id_tutor_docente;
    }

    public void setId_tutor_docente(int id_tutor_docente) {
        this.id_tutor_docente = id_tutor_docente;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}