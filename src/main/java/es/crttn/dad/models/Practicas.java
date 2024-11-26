package es.crttn.dad.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class Practicas {

    // Attributes as Properties
    private final IntegerProperty id_practica;
    private final IntegerProperty id_alumno;
    private final IntegerProperty id_empresa;
    private final IntegerProperty id_tutor_empresa;
    private final IntegerProperty id_tutor_docente;
    private final ObjectProperty<Date> fecha_inicio;
    private final ObjectProperty<Date> fecha_fin;

    // Constructor
    public Practicas(int id_practica, int id_alumno, int id_empresa, int id_tutor_empresa, int id_tutor_docente, Date fecha_inicio, Date fecha_fin) {
        this.id_practica = new SimpleIntegerProperty(id_practica);
        this.id_alumno = new SimpleIntegerProperty(id_alumno);
        this.id_empresa = new SimpleIntegerProperty(id_empresa);
        this.id_tutor_empresa = new SimpleIntegerProperty(id_tutor_empresa);
        this.id_tutor_docente = new SimpleIntegerProperty(id_tutor_docente);
        this.fecha_inicio = new SimpleObjectProperty<>(fecha_inicio);
        this.fecha_fin = new SimpleObjectProperty<>(fecha_fin);
    }

    // Getters and Setters for Properties
    public IntegerProperty id_practicaProperty() {
        return id_practica;
    }

    public int getId_practica() {
        return id_practica.get();
    }

    public void setId_practica(int id_practica) {
        this.id_practica.set(id_practica);
    }

    public IntegerProperty id_alumnoProperty() {
        return id_alumno;
    }

    public int getId_alumno() {
        return id_alumno.get();
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno.set(id_alumno);
    }

    public IntegerProperty id_empresaProperty() {
        return id_empresa;
    }

    public int getId_empresa() {
        return id_empresa.get();
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa.set(id_empresa);
    }

    public IntegerProperty id_tutor_empresaProperty() {
        return id_tutor_empresa;
    }

    public int getId_tutor_empresa() {
        return id_tutor_empresa.get();
    }

    public void setId_tutor_empresa(int id_tutor_empresa) {
        this.id_tutor_empresa.set(id_tutor_empresa);
    }

    public IntegerProperty id_tutor_docenteProperty() {
        return id_tutor_docente;
    }

    public int getId_tutor_docente() {
        return id_tutor_docente.get();
    }

    public void setId_tutor_docente(int id_tutor_docente) {
        this.id_tutor_docente.set(id_tutor_docente);
    }

    public ObjectProperty<Date> fecha_inicioProperty() {
        return fecha_inicio;
    }

    public Date getFecha_inicio() {
        return fecha_inicio.get();
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio.set(fecha_inicio);
    }

    public ObjectProperty<Date> fecha_finProperty() {
        return fecha_fin;
    }

    public Date getFecha_fin() {
        return fecha_fin.get();
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin.set(fecha_fin);
    }
}
