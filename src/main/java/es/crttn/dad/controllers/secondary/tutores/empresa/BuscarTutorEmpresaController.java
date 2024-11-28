package es.crttn.dad.controllers.secondary.tutores.empresa;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.Tutor_docente;
import es.crttn.dad.models.Tutor_empresa;
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
import java.util.ResourceBundle;

public class BuscarTutorEmpresaController implements Initializable {

    @FXML
    private TableColumn<Tutor_empresa, String> apellidoColumn;

    @FXML
    private TableColumn<Tutor_empresa, String> correoColumn;

    @FXML
    private TableColumn<Tutor_empresa, Integer> idColumn;

    @FXML
    private TableColumn<Tutor_empresa, Integer> telefonoColumn;

    @FXML
    private TextField idTutorEmpresaTexfield;

    @FXML
    private TableColumn<Tutor_empresa, String> nombreColumn;

    @FXML
    private TableView<?> tutoresEmpresaTableView;

    @FXML
    private BorderPane root;

    private StringProperty idTutorEmpresaProperty = new SimpleStringProperty();
    ObservableList listaTutoresEmpresa;

    public BuscarTutorEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/tutores/empresa/BuscarTutorEmprView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idTutorEmpresaTexfield.textProperty().bindBidirectional(idTutorEmpresaProperty);
        //Elimina el prompText y quita el focus del textField
        idTutorEmpresaTexfield.setText("");
        idTutorEmpresaTexfield.setFocusTraversable(false);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().id_tutor_empresaProperty().asObject());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidoColumn.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        correoColumn.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
        telefonoColumn.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty().asObject());

        listaTutoresEmpresa = FXCollections.observableArrayList();
        tutoresEmpresaTableView.setItems(listaTutoresEmpresa);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAllAction(ActionEvent event) {
        String querry = "SELECT * FROM tutorempresa";

        listaTutoresEmpresa.clear();

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Tutor_empresa tutorEmpresa = new Tutor_empresa(
                            resultSet.getInt("id_tutor_empresa"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("correo"),
                            resultSet.getInt("telefono"));
                    listaTutoresEmpresa.add(tutorEmpresa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        listaTutoresEmpresa.clear();

        String querry = "SELECT * FROM tutorempresa where id_tutor_empresa = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, idTutorEmpresaProperty.getValue());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tutor_empresa tutorEmpresa = new Tutor_empresa(
                            resultSet.getInt("id_tutor_empresa"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("correo"),
                            resultSet.getInt("telefono"));
                    listaTutoresEmpresa.add(tutorEmpresa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGtc().getGtec().getRoot());
    }
}
