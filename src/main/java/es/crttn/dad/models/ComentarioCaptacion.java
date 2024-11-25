package es.crttn.dad.models;

import java.util.Date;

public class ComentarioCaptacion {

    //Atributos
    private int idComentario;
    private Date fecha;
    private String comentario;
    private int idEmpresa;

    // Constructor
    public ComentarioCaptacion(int idComentario, Date fecha, String comentario, int idEmpresa) {
        this.idComentario = idComentario;
        this.fecha = fecha;
        this.comentario = comentario;
        this.idEmpresa = idEmpresa;
    }

    // Getters y setters
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public String toString() {
        return "ComentarioCaptacion{" +
                "idComentario=" + idComentario +
                ", fecha=" + fecha +
                ", comentario='" + comentario + '\'' +
                ", idEmpresa=" + idEmpresa +
                '}';
    }
}