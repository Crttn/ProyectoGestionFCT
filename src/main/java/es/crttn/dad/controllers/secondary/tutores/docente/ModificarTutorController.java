package es.crttn.dad.controllers.secondary.tutores.docente;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModificarTutorController implements Initializable {

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField idDoncenteTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private BorderPane root;

    private StringProperty idDocenteProperty = new SimpleStringProperty();
    private StringProperty nombreProperty = new SimpleStringProperty();
    private StringProperty appellidoProperty = new SimpleStringProperty();
    private StringProperty correoProperty = new SimpleStringProperty();


    public ModificarTutorController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/tutores/docente/ModificarDocenteView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idDoncenteTextField.textProperty().bindBidirectional(idDocenteProperty);
        nombreTextField.textProperty().bindBidirectional(nombreProperty);
        apellidoTextField.textProperty().bindBidirectional(appellidoProperty);
        correoTextField.textProperty().bindBidirectional(correoProperty);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        String querry = "SELECT nombre, apellido, correo FROM tutordocente WHERE id_tutor_docente = ?";


        if (idDocenteProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, idDoncenteTextField.getText());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        nombreProperty.setValue(resultSet.getString("nombre"));
                        appellidoProperty.setValue(resultSet.getString("apellido"));
                        correoProperty.setValue(resultSet.getString("correo"));
                    }
                }

                nombreTextField.setText(nombreProperty.getValue());
                apellidoTextField.setText(appellidoProperty.getValue());
                correoTextField.setText(correoProperty.getValue());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onModifyAction(ActionEvent event) {

        // Validar los campos antes de continuar
        if (!validarCampos()) {
            return;
        }

        String querry = "UPDATE tutordocente SET nombre = ?, apellido = ?, correo = ? WHERE id_tutor_docente = ?";

        if (idDocenteProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, nombreProperty.getValue());
                statement.setString(2, appellidoProperty.get());
                statement.setString(3, correoProperty.get());
                statement.setString(4, idDocenteProperty.getValue());

                //Ejecuta la consulta
                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modificación Realizada");
                alert.setHeaderText("Se ha realizado correctamente la modificación de los datos.");
                alert.showAndWait();

                nombreTextField.setText("");
                apellidoTextField.setText("");
                correoTextField.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }
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
        if (correoTextField.getText()== null || !correoTextField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Correo");
            alert.setHeaderText("El correo no sigue el formato adecuado.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    //Verificar si el alumno existe en la base

    private boolean existeDINAlumnoEnBaseDeDatos(int idAlumno) {
        String query = "SELECT COUNT(*) FROM alumno WHERE dni = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idAlumno);
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
