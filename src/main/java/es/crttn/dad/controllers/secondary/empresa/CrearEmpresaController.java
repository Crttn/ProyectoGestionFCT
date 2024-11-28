package es.crttn.dad.controllers.secondary.empresa;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CrearEmpresaController implements Initializable {


    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField correoEmpresa;

    @FXML
    private TextField direccionEmpresa;

    @FXML
    private TextField horarioEmpresa;

    @FXML
    private TextField nombreEmpresa;

    @FXML
    private TextField idTutorEmpresa;

    @FXML
    private TextField plazasDisponibles;

    @FXML
    private TextField especialidadEmpresa;

    @FXML
    private BorderPane root;

    StringProperty nombreProperty = new SimpleStringProperty();
    StringProperty direccionProperty = new SimpleStringProperty();
    StringProperty correoProperty = new SimpleStringProperty();
    StringProperty horarioProperty = new SimpleStringProperty();
    StringProperty plazasProperty = new SimpleStringProperty();
    IntegerProperty nombreTutorProperty = new SimpleIntegerProperty();
    StringProperty especialidadProperty = new SimpleStringProperty();

    public CrearEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/empresa/CrearEmpresaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nombreEmpresa.textProperty().bindBidirectional(nombreProperty);
        direccionEmpresa.textProperty().bindBidirectional(direccionProperty);
        correoEmpresa.textProperty().bindBidirectional(correoProperty);
        horarioEmpresa.textProperty().bindBidirectional(horarioProperty);
        plazasDisponibles.textProperty().bindBidirectional(plazasProperty);
        idTutorEmpresa.textProperty().bindBidirectional(nombreTutorProperty, new NumberStringConverter());
        especialidadEmpresa.textProperty().bindBidirectional(especialidadProperty);

        idTutorEmpresa.setText("");
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onCrearEmpresaAction(ActionEvent event) {

        // Validar los campos antes de continuar
        if (!validarCampos()) {
            return;
        }

        String querry = "INSERT INTO empresa (nombre, direccion, correo, horario, plazas_disp, id_tutor_empresa, especialidad) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, nombreProperty.getValue());
            statement.setString(2, direccionProperty.getValue());
            statement.setString(3, correoProperty.getValue());
            statement.setString(4, horarioProperty.getValue());
            statement.setString(5, plazasProperty.getValue());
            statement.setInt(6, nombreTutorProperty.getValue());
            statement.setString(7, especialidadProperty.getValue());

            int filasAfectadas = statement.executeUpdate();
            System.out.println("Usuario insertado: " + filasAfectadas + " fila(s) afectada(s)");

            nombreEmpresa.setText("");
            direccionEmpresa.setText("");
            correoEmpresa.setText("");
            horarioEmpresa.setText("");
            plazasDisponibles.setText("");
            idTutorEmpresa.setText("");
            especialidadEmpresa.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());
    }

    public boolean validarCampos() {

        // Validar Nombre de la Empresa
        if (nombreEmpresa.getText() == null || !nombreEmpresa.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el Nombre");
            alert.setHeaderText("El nombre de la empresa no sigue el formato adecuado.");
            alert.showAndWait();
            return false;
        }

        // Verificar si el nombre ya existe en la base de datos
        if (existeNombreEmpresaEnBaseDeDatos(nombreEmpresa.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el Nombre");
            alert.setHeaderText("El nombre de la empresa ya está registrado en la base de datos.");
            alert.showAndWait();
            return false;
        }

        //Direccion
        if (direccionEmpresa.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la dirección");
            alert.setHeaderText("La direccion no puede ser nula");
            alert.showAndWait();
            return false;
        }

        //Verificar direccion si ya existe en la base de datos
        if (existeDireccionEnBaseDatos(direccionEmpresa.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la direccion");
            alert.setHeaderText("La direccion ya se encuentra registrada.");
            alert.showAndWait();
            return false;
        }

        //Correo
        if (correoEmpresa.getText()== null || !correoEmpresa.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Correo");
            alert.setHeaderText("El correo no sigue el formato adecuado.");
            alert.showAndWait();
            return false;
        }

        //Verificar si correo ya existe en la base
        if (existeCorreoEnBaseDatos(correoEmpresa.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el correo");
            alert.setHeaderText("La direccion de correo ya se encuentra registrada.");
            alert.showAndWait();
            return false;
        }

        //Horario
        if (horarioEmpresa.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el horario");
            alert.setHeaderText("El horario no permite nulo.");
            alert.showAndWait();
            return false;
        }

        //Plazas Disp
        if (plazasDisponibles.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en las plazas");
            alert.setHeaderText("No se permite nulos.");
            alert.showAndWait();
            return false;
        }

        //ID Tutor
        if (idTutorEmpresa.getText() == null || idTutorEmpresa.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el ID tutor");
            alert.setHeaderText("No se permite nulo.");
            alert.showAndWait();
            return false;
        }

        String idTutorEmpresaStr = idTutorEmpresa.getText();
        try {
            int idTutorEmpresa = Integer.parseInt(idTutorEmpresaStr);
            if (!existeIdTutorEmpresaEnBaseDeDatos(idTutorEmpresa)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en ID Tutor Empresa");
                alert.setHeaderText("El ID Tutor Empresa no coincide con ningún registro en la base de datos.");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Tutor Empresa");
            alert.setHeaderText("El ID Tutor Empresa debe ser un número entero.");
            alert.showAndWait();
            return false;
        }

        //Especialidad
        if (especialidadEmpresa.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la especialidad");
            alert.setHeaderText("La especialidad no puede ser nula.");
            alert.showAndWait();
            return false;
        }

        return true; // Todos los campos son válidos

    }

    //VERIFICACIONES

    private boolean existeNombreEmpresaEnBaseDeDatos(String nombreEmpresa) {
        String query = "SELECT COUNT(*) FROM empresa WHERE nombre = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nombreEmpresa);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Devuelve true si ya existe.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si hay un error, considera que no existe.
    }

    private boolean existeDireccionEnBaseDatos(String direccion) {
        String query = "SELECT COUNT(*) FROM empresa WHERE direccion = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, direccion);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Devuelve true si ya existe.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private  boolean existeCorreoEnBaseDatos(String correo) {
        String query = "SELECT COUNT(*) FROM empresa WHERE correo = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Devuelve true si ya existe.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean existeIdTutorEmpresaEnBaseDeDatos(int idTutorEmpresa) {
        String query = "SELECT COUNT(*) FROM tutorempresa WHERE id_tutor_empresa = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idTutorEmpresa);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
