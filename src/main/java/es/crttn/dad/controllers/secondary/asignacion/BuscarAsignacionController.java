package es.crttn.dad.controllers.secondary.asignacion;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.Practicas;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class BuscarAsignacionController implements Initializable {

    @FXML
    private TableView<Practicas> asignacionTableView;

    @FXML
    private TableColumn<Practicas, Date> fechaFinColumn;

    @FXML
    private TableColumn<Practicas, Date> fechaInicioColumn;

    @FXML
    private TableColumn<Practicas, Integer> idAlumnoColumn;

    @FXML
    private TableColumn<Practicas, Integer> idEmpresaColumn;

    @FXML
    private TableColumn<Practicas, Integer> idPracticaColumn;

    @FXML
    private TableColumn<Practicas, Integer> idTutorDocenteColumn;

    @FXML
    private TableColumn<Practicas, Integer> idTutorEmpresaColumn;

    @FXML
    private TableColumn<Practicas, String> nombreAlumnoColumn;

    @FXML
    private TableColumn<Practicas, String> nombreDocenteColumn;

    @FXML
    private TableColumn<Practicas, String> nombreEmpresaColumn;

    @FXML
    private TableColumn<Practicas, String> nombreTutorEmpresaColumn;

    @FXML
    private TextField idcomentariotextfield;

    @FXML
    private BorderPane root;

    private final IntegerProperty idPracticaProperty = new SimpleIntegerProperty();
    private ObservableList listaPracticas;

    public BuscarAsignacionController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/asignacionpracticas/BuscarAsignacion.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idcomentariotextfield.textProperty().bindBidirectional(idPracticaProperty, new NumberStringConverter());
        idcomentariotextfield.setText("");
        idcomentariotextfield.setFocusTraversable(false);

        idPracticaColumn.setCellValueFactory(cellData -> cellData.getValue().id_practicaProperty().asObject());
        idAlumnoColumn.setCellValueFactory(cellData -> cellData.getValue().id_alumnoProperty().asObject());
        nombreAlumnoColumn.setCellValueFactory(cellData -> cellData.getValue().nombre_alumno());
        idEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().id_empresaProperty().asObject());
        nombreEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().nombre_empresaProperty());
        idTutorDocenteColumn.setCellValueFactory(cellData -> cellData.getValue().id_tutor_docenteProperty().asObject());
        nombreDocenteColumn.setCellValueFactory(cellData -> cellData.getValue().nombre_tutor_docenteProperty());
        idTutorEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().id_tutor_empresaProperty().asObject());
        nombreTutorEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().nombre_tutor_empresaProperty());
        fechaInicioColumn.setCellValueFactory(cellData -> cellData.getValue().fecha_inicioProperty());
        fechaFinColumn.setCellValueFactory(cellData -> cellData.getValue().fecha_finProperty());

        listaPracticas = FXCollections.observableArrayList();
        asignacionTableView.setItems(listaPracticas);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onBuscarAction(ActionEvent event) {
        listaPracticas.clear();

        String querry = "SELECT * FROM practica WHERE id_practica = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, idPracticaProperty.getValue().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Practicas practica = new Practicas(
                            resultSet.getInt("id_practica"),
                            resultSet.getInt("id_alumno"),
                            resultSet.getString("nombre_alumno"),
                            resultSet.getInt("id_empresa"),
                            resultSet.getString("nombre_alumno"),
                            resultSet.getInt("id_tutor_docente"),
                            resultSet.getString("nombre_alumno"),
                            resultSet.getInt("id_tutor_empresa"),
                            resultSet.getString("nombre_alumno"),
                            resultSet.getDate("fecha_inicio"),
                            resultSet.getDate("fecha_fin"));
                    listaPracticas.add(practica);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearchAllAction(ActionEvent event) {
        listaPracticas.clear();

        String querry = "SELECT *, alumno.nombre, empresa.nombre, tutordocente.nombre, tutorempresa.nombre FROM practica \n" +
                "\tINNER JOIN alumno on practica.id_alumno = alumno.id_alumno\n" +
                "    INNER JOIN empresa on practica.id_empresa = empresa.id_empresa\n" +
                "    INNER JOIN tutordocente on practica.id_tutor_docente = tutordocente.id_tutor_docente\n" +
                "    INNER JOIN tutorempresa on practica.id_tutor_empresa = tutorempresa.id_tutor_empresa";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Practicas practica = new Practicas(
                            resultSet.getInt("id_practica"),
                            resultSet.getInt("id_alumno"),
                            resultSet.getString("alumno.nombre"),
                            resultSet.getInt("id_empresa"),
                            resultSet.getString("empresa.nombre"),
                            resultSet.getInt("id_tutor_docente"),
                            resultSet.getString("tutordocente.nombre"),
                            resultSet.getInt("id_tutor_empresa"),
                            resultSet.getString("tutorempresa.nombre"),
                            resultSet.getDate("fecha_inicio"),
                            resultSet.getDate("fecha_fin"));
                    listaPracticas.add(practica);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGasc().getRoot());
    }
}
