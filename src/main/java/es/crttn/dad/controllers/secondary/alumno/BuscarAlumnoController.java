package es.crttn.dad.controllers.secondary.alumno;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.Alumno;
import es.crttn.dad.models.Empresa;
import javafx.beans.property.*;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class BuscarAlumnoController implements Initializable {

    @FXML
    private TextField dniAlumnoTexfield;

    @FXML
    private TableView<Alumno> alumnosTableView;

    @FXML
    private TableColumn<Alumno, String> columnaApellido;

    @FXML
    private TableColumn<Alumno, String> columnaCorreo;

    @FXML
    private TableColumn<Alumno, Integer> columnaCurso;

    @FXML
    private TableColumn<Alumno, String> columnaDni;

    @FXML
    private TableColumn<Alumno, Date> columnaFechaNac;

    @FXML
    private TableColumn<Alumno, Integer> columnaId;

    @FXML
    private TableColumn<Alumno, String> columnaNombre;

    @FXML
    private TableColumn<Alumno, Integer> columnaNuss;

    @FXML
    private TableColumn<Alumno, Integer> columnaTelefono;

    @FXML
    private BorderPane root;

    StringProperty dniProperty = new SimpleStringProperty();
    ObservableList listaAlumnos;

    public BuscarAlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/alumno/BuscarAlumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dniAlumnoTexfield.textProperty().bindBidirectional(dniProperty);

        columnaId.setCellValueFactory(cellData -> cellData.getValue().idAlumnoProperty().asObject());
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        columnaDni.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        columnaCorreo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
        columnaFechaNac.setCellValueFactory(cellData -> cellData.getValue().fechaNacimientoProperty());
        columnaTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty().asObject());
        columnaNuss.setCellValueFactory(cellData -> cellData.getValue().codigoNussProperty().asObject());
        columnaCurso.setCellValueFactory(cellData -> cellData.getValue().idCursoProperty().asObject());

        listaAlumnos = FXCollections.observableArrayList();
        alumnosTableView.setItems(listaAlumnos);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onBuscarAlumnoAction(ActionEvent event) {
        listaAlumnos.clear();

        String querry = "SELECT * FROM alumno where dni = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, dniProperty.getValue());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Alumno alumno = new Alumno(
                    resultSet.getInt("id_alumno"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("dni"),
                    resultSet.getString("correo"),
                    resultSet.getDate("fecha_nacimiento"),
                    resultSet.getInt("telefono"),
                    resultSet.getInt("codigo_nuss"),
                    resultSet.getInt("id_curso") //Si quieres runear usa id_curso o Curso
                    );
                    listaAlumnos.add(alumno);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearchAllAction(ActionEvent event) {
        String querry = "SELECT * FROM alumno";

        listaAlumnos.clear();

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Alumno alumno = new Alumno(
                            resultSet.getInt("id_alumno"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("dni"),
                            resultSet.getString("correo"),
                            resultSet.getDate("fecha_nacimiento"),
                            resultSet.getInt("telefono"),
                            resultSet.getInt("codigo_nuss"),
                            resultSet.getInt("id_curso") //Si quieres runear usa id_curso o Curso
                    );
                    listaAlumnos.add(alumno);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGalc().getRoot());
    }
}
