package es.crttn.dad.models;

import java.sql.Date;

public class SeguimientoCaptacion {

    // Atributos
    private int idCaptacion;
    private int idTutor;
    private int idEmpresa;
    private Date fecha;
    private String registro;

    // Constructor
    public SeguimientoCaptacion(int idCaptacion, int idTutor, int idEmpresa, Date fecha, String registro) {
        this.idCaptacion = idCaptacion;
        this.idTutor = idTutor;
        this.idEmpresa = idEmpresa;
        this.fecha = fecha;
        this.registro = registro;
    }

    // Getters y setters
    public int getIdCaptacion() {
        return idCaptacion;
    }

    public void setIdCaptacion(int idCaptacion) {
        this.idCaptacion = idCaptacion;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "SeguimientoCaptacion{" +
                "idCaptacion=" + idCaptacion +
                ", idTutor=" + idTutor +
                ", idEmpresa=" + idEmpresa +
                ", fecha=" + fecha +
                ", registro='" + registro + '\'' +
                '}';
    }
}
