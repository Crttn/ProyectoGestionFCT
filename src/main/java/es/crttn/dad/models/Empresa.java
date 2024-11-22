package es.crttn.dad.models;

public class Empresa {

    // Atributos
    private int id_empresa;
    private String nombre;
    private String direccion;
    private String correo;
    private String horario;
    private int plazas_disp;
    private String especialidad;
    private int id_tutor_empresa;

    // Constructor
    public Empresa(int id_empresa, String nombre, String direccion, String correo, String horario, int plazas_disp, String especialidad, int id_tutor_empresa) {
        this.id_empresa = id_empresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.horario = horario;
        this.plazas_disp = plazas_disp;
        this.especialidad = especialidad;
        this.id_tutor_empresa = id_tutor_empresa;
    }

    // Getters y Setters
    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getPlazas_disp() {
        return plazas_disp;
    }

    public void setPlazas_disp(int plazas_disp) {
        this.plazas_disp = plazas_disp;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getId_tutor_empresa() {
        return id_tutor_empresa;
    }

    public void setId_tutor_empresa(int id_tutor_empresa) {
        this.id_tutor_empresa = id_tutor_empresa;
    }
}
