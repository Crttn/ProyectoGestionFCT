package es.crttn.dad.controllers.secondary.tutores.docente;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.Alumno;
import es.crttn.dad.models.Tutor_docente;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public class BuscarTutorController implements Initializable {

    @FXML
    private TableColumn<Tutor_docente, String> apellidoColumn;

    @FXML
    private TableColumn<Tutor_docente, String> correoColumn;

    @FXML
    private TableView<Tutor_docente> docentesTableView;

    @FXML
    private TableColumn<Tutor_docente, Integer> idColumn;

    @FXML
    private TextField idDocenteTexfield;

    @FXML
    private TableColumn<Tutor_docente, String> nombreColumn;

    @FXML
    private BorderPane root;

    private StringProperty idDocenteProperty = new SimpleStringProperty();
    ObservableList listaDocentes;

    public BuscarTutorController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/tutores/docente/BuscarDocenteView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idDocenteTexfield.textProperty().bindBidirectional(idDocenteProperty);
        //Elimina el prompText y quita el focus del textField
        idDocenteTexfield.setText("");
        idDocenteTexfield.setFocusTraversable(false);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().id_tutor_docenteProperty().asObject());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidoColumn.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        correoColumn.setCellValueFactory(cellData -> cellData.getValue().correoProperty());

        listaDocentes = FXCollections.observableArrayList();
        docentesTableView.setItems(listaDocentes);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAllAction(ActionEvent event) {
        String querry = "SELECT * FROM tutordocente";

        listaDocentes.clear();

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Tutor_docente docente = new Tutor_docente(
                            resultSet.getInt("id_tutor_docente"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("correo"));
                    listaDocentes.add(docente);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBuscarAlumnoAction(ActionEvent event) {
        listaDocentes.clear();

        String querry = "SELECT * FROM tutordocente where id_tutor_docente = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, idDocenteProperty.getValue());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tutor_docente docente = new Tutor_docente(
                            resultSet.getInt("id_tutor_docente"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("correo"));
                    listaDocentes.add(docente);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGtc().getGdc().getRoot());
    }
}
