package es.crttn.dad.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class ComentarioCaptacion {

    // Atributos como Properties
    private final IntegerProperty idComentario;
    private final ObjectProperty<Date> fecha;
    private final StringProperty comentario;
    private final IntegerProperty idEmpresa;

    // Constructor
    public ComentarioCaptacion(int idComentario, Date fecha, String comentario, int idEmpresa) {
        this.idComentario = new SimpleIntegerProperty(idComentario);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.comentario = new SimpleStringProperty(comentario);
        this.idEmpresa = new SimpleIntegerProperty(idEmpresa);
    }

    // Getters y Setters para las Properties
    public IntegerProperty idComentarioProperty() {
        return idComentario;
    }

    public int getIdComentario() {
        return idComentario.get();
    }

    public void setIdComentario(int idComentario) {
        this.idComentario.set(idComentario);
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

    public StringProperty comentarioProperty() {
        return comentario;
    }

    public String getComentario() {
        return comentario.get();
    }

    public void setComentario(String comentario) {
        this.comentario.set(comentario);
    }

    public IntegerProperty idEmpresaProperty() {
        return idEmpresa;
    }

    public int getIdEmpresa() {
        return idEmpresa.get();
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa.set(idEmpresa);
    }

    @Override
    public String toString() {
        return "ComentarioCaptacion{" +
                "idComentario=" + idComentario.get() +
                ", fecha=" + fecha.get() +
                ", comentario='" + comentario.get() + '\'' +
                ", idEmpresa=" + idEmpresa.get() +
                '}';
    }
}
