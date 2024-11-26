package es.crttn.dad.controllers.secondary.asignacion;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.Alumno;
import es.crttn.dad.models.Practicas;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TextField idcomentariotextfield;

    @FXML
    private BorderPane root;

    private IntegerProperty idPracticaProperty = new SimpleIntegerProperty();
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

        idPracticaColumn.setCellValueFactory(cellData -> cellData.getValue().id_practicaProperty().asObject());
        idAlumnoColumn.setCellValueFactory(cellData -> cellData.getValue().id_alumnoProperty().asObject());
        idEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().id_empresaProperty().asObject());
        idTutorDocenteColumn.setCellValueFactory(cellData -> cellData.getValue().id_tutor_docenteProperty().asObject());
        idTutorEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().id_tutor_empresaProperty().asObject());
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

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, idPracticaProperty.getValue().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Practicas practica = new Practicas(
                            resultSet.getInt("id_practica"),
                            resultSet.getInt("id_alumno"),
                            resultSet.getInt("id_empresa"),
                            resultSet.getInt("id_tutor_docente"),
                            resultSet.getInt("id_tutor_empresa"),
                            resultSet.getDate("fecha_inicio"),
                            resultSet.getDate("fecha_fin")
                    );
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
