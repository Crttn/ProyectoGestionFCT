package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.VisitaSeguimiento;
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

public class BuscarVisitaController implements Initializable {

    @FXML
    private TableColumn<VisitaSeguimiento, Date> fechaColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, Integer> idAlumnoColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, Integer> idDocentenColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, Integer> idPracticaColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, Integer> idVisitaColumn;

    @FXML
    private TextField idVisitaTextField;

    @FXML
    private TableColumn<VisitaSeguimiento, String> nombreAlumnoColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, String> nombreDocenteColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, String> observacionesColumn;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<?> visitasTableView;

    private final IntegerProperty idVisitaProperty = new SimpleIntegerProperty();
    private ObservableList listaVisitas;


    public BuscarVisitaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/visitasSeguimiento/BuscarVisitasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idVisitaTextField.textProperty().bindBidirectional(idVisitaProperty, new NumberStringConverter());
        idVisitaTextField.setText("");
        idVisitaTextField.setFocusTraversable(false);

        idVisitaColumn.setCellValueFactory(cellData -> cellData.getValue().idVisitaProperty().asObject());
        idPracticaColumn.setCellValueFactory(cellData -> cellData.getValue().idPracticaProperty().asObject());
        idAlumnoColumn.setCellValueFactory(cellData -> cellData.getValue().idAlumnoProperty().asObject());
        nombreAlumnoColumn.setCellValueFactory(cellData -> cellData.getValue().nombreAlumnoProperty());
        idDocentenColumn.setCellValueFactory(cellData -> cellData.getValue().idDocenteProperty().asObject());
        nombreDocenteColumn.setCellValueFactory(cellData -> cellData.getValue().nombreDocenteProperty());
        fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        observacionesColumn.setCellValueFactory(cellData -> cellData.getValue().observacionesProperty());

        listaVisitas = FXCollections.observableArrayList();
        visitasTableView.setItems(listaVisitas);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        listaVisitas.clear();

        String querry = "SELECT \n" +
                "  visitaseguimiento.*, \n" +
                "  practica.id_practica as idPractica, \n" +
                "  alumno.id_alumno as idAlumno, \n" +
                "  alumno.nombre as alumnoNombre, \n" +
                "  id_tutor_docente as idDocente, \n" +
                "  nombre as nombreDocente \n" +
                "FROM \n" +
                "  visitaseguimiento \n" +
                "  INNER JOIN practica on practica.id_practica = visitaseguimiento.id_visita \n" +
                "  INNER JOIN alumno on alumno.id_alumno = practica.id_alumno \n" +
                "WHERE \n" +
                "  id_visita = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, idVisitaProperty.getValue().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    VisitaSeguimiento visitaSeguimiento = new VisitaSeguimiento(
                            resultSet.getInt("id_visita"),
                            resultSet.getInt("idPractica"),
                            resultSet.getInt("idAlumno"),
                            resultSet.getString("alumnoNombre"),
                            resultSet.getInt("idDocente"),
                            resultSet.getString("nombreDocente"),
                            resultSet.getDate("fecha"),
                            resultSet.getString("observaciones"));
                    listaVisitas.add(visitaSeguimiento);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onSearchAllAction(ActionEvent event) {
        listaVisitas.clear();

        String querry = "SELECT \n" +
                "  visitaseguimiento.*, \n" +
                "  practica.id_practica as idPractica, \n" +
                "  alumno.id_alumno as idAlumno, \n" +
                "  alumno.nombre as alumnoNombre, \n" +
                "  id_tutor_docente as idDocente, \n" +
                "  nombre as nombreDocente \n" +
                "FROM \n" +
                "  visitaseguimiento \n" +
                "  INNER JOIN practica on practica.id_practica = visitaseguimiento.id_visita \n" +
                "  INNER JOIN alumno on alumno.id_alumno = practica.id_alumno";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    VisitaSeguimiento visitaSeguimiento = new VisitaSeguimiento(
                            resultSet.getInt("id_visita"),
                            resultSet.getInt("idPractica"),
                            resultSet.getInt("idAlumno"),
                            resultSet.getString("alumnoNombre"),
                            resultSet.getInt("idDocente"),
                            resultSet.getString("nombreDocente"),
                            resultSet.getDate("fecha"),
                            resultSet.getString("observaciones"));
                    listaVisitas.add(visitaSeguimiento);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
