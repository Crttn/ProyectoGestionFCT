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

public class ModificarEmpresaController implements Initializable {

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField direccionTextField;

    @FXML
    private TextField especialidadTextFiedl;

    @FXML
    private TextField horarioTextField;

    @FXML
    private TextField nombreEmpresaTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField plazasTextField;

    @FXML
    private TextField idTutorTextFIeld;

    @FXML
    private BorderPane root;

    private final StringProperty nombreProperty = new SimpleStringProperty();
    private final StringProperty direccionProperty = new SimpleStringProperty();
    private final StringProperty correoProperty = new SimpleStringProperty();
    private final StringProperty horarioProperty = new SimpleStringProperty();
    private final IntegerProperty plazasProperty = new SimpleIntegerProperty();
    private final IntegerProperty idTutorProperty = new SimpleIntegerProperty();
    private final StringProperty especialidadProperty = new SimpleStringProperty();

    public ModificarEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/empresa/ModificarEmpresaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nombreEmpresaTextField.textProperty().bindBidirectional(nombreProperty);
        direccionTextField.textProperty().bindBidirectional(direccionProperty);
        correoTextField.textProperty().bindBidirectional(correoProperty);
        horarioTextField.textProperty().bindBidirectional(horarioProperty);
        plazasTextField.textProperty().bindBidirectional(plazasProperty, new NumberStringConverter());
        idTutorTextFIeld.textProperty().bindBidirectional(idTutorProperty, new NumberStringConverter());
        especialidadTextFiedl.textProperty().bindBidirectional(especialidadProperty);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        String querry = "SELECT nombre, direccion, correo, horario, plazas_disp, especialidad, id_tutor_empresa FROM empresa WHERE nombre = ?";


        if (nombreProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, nombreProperty.getValue());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        nombreProperty.setValue(resultSet.getString("nombre"));
                        direccionProperty.setValue(resultSet.getString("direccion"));
                        correoProperty.setValue(resultSet.getString("correo"));
                        horarioProperty.setValue(resultSet.getString("horario"));
                        plazasProperty.setValue(resultSet.getInt("plazas_disp"));
                        especialidadProperty.setValue(resultSet.getString("especialidad"));
                        idTutorProperty.setValue(resultSet.getInt("id_tutor_empresa"));

                    }
                }

                nombreTextField.setText(nombreProperty.getValue());
                direccionTextField.setText(direccionProperty.getValue());
                correoTextField.setText(correoProperty.getValue());
                horarioTextField.setText(horarioProperty.getValue());
                plazasTextField.setText(plazasProperty.getValue().toString());
                especialidadTextFiedl.setText(especialidadProperty.getValue());
                idTutorTextFIeld.setText(idTutorProperty.getValue().toString());

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

        String querry = "UPDATE empresa SET nombre = ?, direccion = ?, correo = ?, horario = ?, plazas_disp = ?, especialidad = ?, id_tutor_empresa = ? WHERE nombre = ?";

        if (nombreProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, nombreProperty.getValue());
                statement.setString(2, direccionProperty.get());
                statement.setString(3, correoProperty.getValue());
                statement.setString(4, horarioProperty.get());
                statement.setInt(5, plazasProperty.getValue());  // Ejemplo: plazas disponibles
                statement.setString(6, especialidadProperty.getValue());
                statement.setInt(7, idTutorProperty.getValue());
                statement.setString(8, nombreProperty.getValue()); // Nombre usado en el WHERE

                //Ejecuta la consulta
                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Midificación Realizada");
                alert.setHeaderText("Se ha realizado correctamente la modificación de los datos.");
                alert.showAndWait();

                nombreTextField.setText("");
                direccionTextField.setText("");
                correoTextField.setText("");
                horarioTextField.setText("");
                plazasTextField.setText("");
                especialidadTextFiedl.setText("");
                idTutorTextFIeld.setText("");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());
    }

    public boolean validarCampos() {

        // Validar Nombre de la Empresa
        if (nombreEmpresaTextField.getText() == null || !nombreEmpresaTextField.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el Nombre");
            alert.setHeaderText("El nombre de la empresa no sigue el formato adecuado.");
            alert.showAndWait();
            return false;
        }

        //Direccion
        if (direccionTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la dirección");
            alert.setHeaderText("La direccion no puede ser nula");
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

        //Horario
        if (horarioTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el horario");
            alert.setHeaderText("El horario no permite nulo.");
            alert.showAndWait();
            return false;
        }

        //Plazas Disp
        if (plazasTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en las plazas");
            alert.setHeaderText("No se permite nulos.");
            alert.showAndWait();
            return false;
        }

        //ID Tutor
        if (idTutorTextFIeld.getText() == null || idTutorTextFIeld.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el ID tutor");
            alert.setHeaderText("No se permite nulo.");
            alert.showAndWait();
            return false;
        }

        //Especialidad
        if (especialidadTextFiedl.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la especialidad");
            alert.setHeaderText("La especialidad no puede ser nula.");
            alert.showAndWait();
            return false;
        }

        return true; // Todos los campos son válidos

    }
}
