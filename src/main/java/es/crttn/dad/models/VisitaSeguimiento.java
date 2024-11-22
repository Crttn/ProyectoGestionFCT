package es.crttn.dad.models;

import java.util.Date;

public class VisitaSeguimiento {

    //Atributos
    private int idVisita;
    private int idPractica;
    private Date fecha;
    private String observaciones;

    // Constructor
    public VisitaSeguimiento(int idVisita, int idPractica, Date fecha, String observaciones) {
        this.idVisita = idVisita;
        this.idPractica = idPractica;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getIdPractica() {
        return idPractica;
    }

    public void setIdPractica(int idPractica) {
        this.idPractica = idPractica;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Visitaseguimiento{" +
                "idVisita=" + idVisita +
                ", idPractica=" + idPractica +
                ", fecha=" + fecha +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
