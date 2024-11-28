package es.crttn.dad.controllers.secondary.empresa;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.Empresa;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class BuscarEmpresaController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Empresa> empresasTableView;

    @FXML
    private TableColumn<Empresa, String> columnacorreo;

    @FXML
    private TableColumn<Empresa, String> columnadireccion;

    @FXML
    private TableColumn<Empresa, String> columnaespecialidad;

    @FXML
    private TableColumn<Empresa, String> columnahorario;

    @FXML
    private TableColumn<Empresa, Integer> columnaidempresa;

    @FXML
    private TableColumn<Empresa, Integer> columnaidtutorempresa;

    @FXML
    private TableColumn<Empresa, String> columnanombre;

    @FXML
    private TableColumn<Empresa, Integer> columnaplazas;

    @FXML
    private Button buscarButton;

    @FXML
    private TextField nombreTextField;

    @FXML
    private BorderPane root;

    private final StringProperty nombreProperty = new SimpleStringProperty();
    private ObservableList listEmpresas;

    public BuscarEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/empresa/BuscarEmpresaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nombreTextField.textProperty().bindBidirectional(nombreProperty);
        nombreTextField.setText("");
        nombreTextField.setFocusTraversable(false);

        columnaidempresa.setCellValueFactory(cellData -> cellData.getValue().id_empresaProperty().asObject());
        columnanombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnadireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        columnacorreo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
        columnahorario.setCellValueFactory(cellData -> cellData.getValue().horarioProperty());
        columnaplazas.setCellValueFactory(cellData -> cellData.getValue().plazas_dispProperty().asObject());
        columnaespecialidad.setCellValueFactory(cellData -> cellData.getValue().especialidadProperty());
        columnaidtutorempresa.setCellValueFactory(cellData -> cellData.getValue().id_tutor_empresaProperty().asObject());

        listEmpresas = FXCollections.observableArrayList();
        empresasTableView.setItems(listEmpresas);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onBuscarAlumnoAction(ActionEvent event) {

        String querry = "SELECT * FROM empresa WHERE nombre = ?";

        listEmpresas.clear();

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            statement.setString(1, nombreProperty.getValue());

            try (ResultSet resultSet = statement.executeQuery()) {
                boolean found = false;

                while (resultSet.next()) {
                    found = true;
                    Empresa empresa = new Empresa(
                            resultSet.getInt("id_empresa"),
                            resultSet.getString("nombre"),
                            resultSet.getString("direccion"),
                            resultSet.getString("correo"),
                            resultSet.getString("horario"),
                            resultSet.getInt("plazas_disp"),
                            resultSet.getString("especialidad"),
                            resultSet.getInt("id_tutor_empresa")
                    );
                    listEmpresas.add(empresa);
                }
                if (!found) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Búsqueda de Empresa");
                    alert.setHeaderText("Empresa no encontrada");
                    alert.setContentText("No se encontró ninguna empresa con el nombre: " + nombreProperty.getValue() + " .");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearchAllAction(ActionEvent event) {

        String querry = "SELECT * FROM empresa";

        listEmpresas.clear();

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Empresa empresa = new Empresa(
                            resultSet.getInt("id_empresa"),
                            resultSet.getString("nombre"),
                            resultSet.getString("direccion"),
                            resultSet.getString("correo"),
                            resultSet.getString("horario"),
                            resultSet.getInt("plazas_disp"),
                            resultSet.getString("especialidad"),
                            resultSet.getInt("id_tutor_empresa")
                    );
                    listEmpresas.add(empresa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());
    }

}
