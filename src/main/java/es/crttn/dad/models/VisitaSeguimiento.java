package es.crttn.dad.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class VisitaSeguimiento {

    // Atributos como Properties
    private final IntegerProperty idVisita;
    private final IntegerProperty idPractica;
    private final IntegerProperty idAlumno;
    private final StringProperty nombreAlumno;
    private final IntegerProperty idDocente;
    private final StringProperty nombreDocente;
    private final ObjectProperty<Date> fecha;
    private final StringProperty observaciones;

    // Constructor
    public VisitaSeguimiento(int idVisita, int idPractica, int idAlumno, String nombreAlumno, int idDocente, String nombreDocente, Date fecha, String observaciones) {
        this.idVisita = new SimpleIntegerProperty(idVisita);
        this.idPractica = new SimpleIntegerProperty(idPractica);
        this.idAlumno = new SimpleIntegerProperty(idAlumno);
        this.nombreAlumno = new SimpleStringProperty(nombreAlumno);
        this.idDocente = new SimpleIntegerProperty(idDocente);
        this.nombreDocente = new SimpleStringProperty(nombreDocente);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.observaciones = new SimpleStringProperty(observaciones);
    }

    // Getters y Setters para las Properties
    public IntegerProperty idVisitaProperty() {
        return idVisita;
    }

    public int getIdVisita() {
        return idVisita.get();
    }

    public void setIdVisita(int idVisita) {
        this.idVisita.set(idVisita);
    }

    public IntegerProperty idPracticaProperty() {
        return idPractica;
    }

    public int getIdPractica() {
        return idPractica.get();
    }

    public void setIdPractica(int idPractica) {
        this.idPractica.set(idPractica);
    }

    public IntegerProperty idAlumnoProperty() {
        return idAlumno;
    }


    public StringProperty nombreAlumnoProperty() {
        return nombreAlumno;
    }


    public IntegerProperty idDocenteProperty() {
        return idDocente;
    }


    public StringProperty nombreDocenteProperty() {
        return nombreDocente;
    }

    public ObjectProperty<Date> fechaProperty() {
        return fecha;
    }

    public Date getFecha() {
        return fecha.get();
    }

    public void setFecha(Date fecha) {
        this.fecha.set(fecha);
    }

    public StringProperty observacionesProperty() {
        return observaciones;
    }

    public String getObservaciones() {
        return observaciones.get();
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }
}
