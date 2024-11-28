package es.crttn.dad.controllers.secondary.tutores.docente;

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
import javafx.scene.control.Alert;
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

public class CrearTutorController implements Initializable {

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField idDocenteTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private BorderPane root;

    private final StringProperty nombreProperty = new SimpleStringProperty();
    private final StringProperty appellidoProperty = new SimpleStringProperty();
    private final StringProperty correoProperty = new SimpleStringProperty();


    public CrearTutorController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/tutores/docente/CrearDocenteView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombreTextField.textProperty().bindBidirectional(nombreProperty);
        apellidoTextField.textProperty().bindBidirectional(appellidoProperty);
        correoTextField.textProperty().bindBidirectional(correoProperty);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddAction(ActionEvent event) {

        // Validar los campos antes de continuar
        if (!validarCampos()) {
            return;
        }

        String querry = "INSERT INTO tutordocente (nombre, apellido, correo) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, nombreProperty.getValue());
            statement.setString(2, appellidoProperty.getValue());
            statement.setString(3, correoProperty.getValue());


            int filasAfectadas = statement.executeUpdate();
            System.out.println("Docente creado: " + filasAfectadas + " fila(s) afectada(s)");

            nombreTextField.setText("");
            apellidoTextField.setText("");
            correoTextField.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGtc().getGdc().getRoot());
    }

    public boolean validarCampos() {

        //Nombre
        if (nombreTextField.getText() == null || !nombreTextField.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Nombre");
            alert.setHeaderText("El Nombre debe contener solo letras");
            alert.showAndWait();
            return false;
        }

        //Apellido
        if (apellidoTextField.getText() == null || !apellidoTextField.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Apellido");
            alert.setHeaderText("El Apellido debe contener solo letras");
            alert.showAndWait();
            return false;
        }

        //Correo
        if (correoTextField.getText() == null || !correoTextField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Correo");
            alert.setHeaderText("El correo no sigue el formato adecuado.");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    //Verificar si el alumno existe en la base

    private boolean existeDocente(int idDoncente) {
        String query = "SELECT COUNT(*) FROM tutordocente WHERE id_tutor_docente = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idDoncente);
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
