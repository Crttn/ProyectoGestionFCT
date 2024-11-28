package es.crttn.dad.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Empresa {

    // Atributos como Properties
    private final IntegerProperty id_empresa;
    private final StringProperty nombre;
    private final StringProperty direccion;
    private final StringProperty correo;
    private final StringProperty horario;
    private final IntegerProperty plazas_disp;
    private final StringProperty especialidad;
    private final IntegerProperty id_tutor_empresa;
    private final StringProperty nombre_tutor_empresa;

    // Constructor
    public Empresa(int id_empresa, String nombre, String direccion, String correo, String horario, int plazas_disp, String especialidad, int id_tutor_empresa, String nombre_tutor_empresa) {
        this.id_empresa = new SimpleIntegerProperty(id_empresa);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccion = new SimpleStringProperty(direccion);
        this.correo = new SimpleStringProperty(correo);
        this.horario = new SimpleStringProperty(horario);
        this.plazas_disp = new SimpleIntegerProperty(plazas_disp);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.id_tutor_empresa = new SimpleIntegerProperty(id_tutor_empresa);
        this.nombre_tutor_empresa = new SimpleStringProperty(nombre_tutor_empresa);
    }

    // Getters y Setters para las Properties
    public IntegerProperty id_empresaProperty() {
        return id_empresa;
    }

    public int getId_empresa() {
        return id_empresa.get();
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa.set(id_empresa);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public StringProperty horarioProperty() {
        return horario;
    }

    public String getHorario() {
        return horario.get();
    }

    public void setHorario(String horario) {
        this.horario.set(horario);
    }

    public IntegerProperty plazas_dispProperty() {
        return plazas_disp;
    }

    public int getPlazas_disp() {
        return plazas_disp.get();
    }

    public void setPlazas_disp(int plazas_disp) {
        this.plazas_disp.set(plazas_disp);
    }

    public StringProperty especialidadProperty() {
        return especialidad;
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad.set(especialidad);
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

    public StringProperty nombre_tutor_empresaProperty() {
        return nombre_tutor_empresa;
    }
    public String getNombre_tutor_empresa() {
        return nombre_tutor_empresa.get();
    }
    public void setNombre_tutor_empresa(String nombre_tutor_empresa) {
        this.nombre_tutor_empresa.set(nombre_tutor_empresa);
    }

}
