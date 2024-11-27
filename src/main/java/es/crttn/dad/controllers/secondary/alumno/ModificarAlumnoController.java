package es.crttn.dad.controllers.secondary.alumno;

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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModificarAlumnoController implements Initializable {

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField codigoNussTextField;

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField cursoTextField;

    @FXML
    private TextField dniTextField;

    @FXML
    private TextField dniSearchTextField;

    @FXML
    private DatePicker fechaNacimientoDatePicker;

    @FXML
    private TextField nombreTextField;

    @FXML
    private BorderPane root;

    @FXML
    private TextField telefonoTextField;

    private StringProperty dniSearchProperty = new SimpleStringProperty();
    private StringProperty nombreProperty = new SimpleStringProperty();
    private StringProperty appellidoProperty = new SimpleStringProperty();
    private StringProperty correoProperty = new SimpleStringProperty();
    private StringProperty dniProperty = new SimpleStringProperty();
    private ObjectProperty<LocalDate> fechaNacimientoProperty = new SimpleObjectProperty<>();
    private IntegerProperty telefonoProperty = new SimpleIntegerProperty();
    private IntegerProperty cursoProperty = new SimpleIntegerProperty();
    private IntegerProperty nussProperty = new SimpleIntegerProperty();

    public ModificarAlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/alumno/ModificarAlumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dniSearchTextField.textProperty().bindBidirectional(dniSearchProperty);
        nombreTextField.textProperty().bindBidirectional(nombreProperty);
        apellidoTextField.textProperty().bindBidirectional(appellidoProperty);
        correoTextField.textProperty().bindBidirectional(correoProperty);
        dniTextField.textProperty().bindBidirectional(dniProperty);
        fechaNacimientoDatePicker.valueProperty().bindBidirectional(fechaNacimientoProperty);
        telefonoTextField.textProperty().bindBidirectional(telefonoProperty, new NumberStringConverter());
        cursoTextField.textProperty().bindBidirectional(cursoProperty, new NumberStringConverter());
        codigoNussTextField.textProperty().bindBidirectional(nussProperty, new NumberStringConverter());
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        String querry = "SELECT nombre, apellido, dni, correo, fecha_nacimiento, telefono, codigo_nuss, id_curso FROM alumno WHERE dni = ?";


        if (dniSearchProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, dniSearchTextField.getText());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        nombreProperty.setValue(resultSet.getString("nombre"));
                        appellidoProperty.setValue(resultSet.getString("apellido"));
                        dniProperty.setValue(resultSet.getString("dni"));
                        correoProperty.setValue(resultSet.getString("correo"));
                        fechaNacimientoProperty.setValue(LocalDate.parse(resultSet.getString("fecha_nacimiento")));
                        telefonoProperty.setValue(resultSet.getInt("telefono"));
                        nussProperty.setValue(resultSet.getInt("codigo_nuss"));
                        cursoProperty.setValue(resultSet.getInt("id_curso"));

                    }
                }

                nombreTextField.setText(nombreProperty.getValue());
                apellidoTextField.setText(appellidoProperty.getValue());
                dniTextField.setText(dniProperty.getValue());
                correoTextField.setText(correoProperty.getValue());
                fechaNacimientoDatePicker.setValue(fechaNacimientoProperty.getValue());
                telefonoTextField.setText(telefonoProperty.getValue().toString());
                codigoNussTextField.setText(nussProperty.getValue().toString());
                cursoTextField.setText(cursoProperty.getValue().toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {
        String querry = "UPDATE alumno SET nombre = ?, apellido = ?, dni = ?, correo = ?, fecha_nacimiento = ?, telefono = ?, codigo_nuss = ?, id_curso = ? WHERE dni = ?";

        if (dniSearchProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, nombreProperty.getValue());
                statement.setString(2, appellidoProperty.get());
                statement.setString(3, dniProperty.getValue());
                statement.setString(4, correoProperty.get());
                statement.setDate(5, Date.valueOf(fechaNacimientoProperty.getValue()));
                statement.setInt(6, telefonoProperty.getValue());
                statement.setInt(7, nussProperty.getValue());
                statement.setInt(8, cursoProperty.getValue());
                statement.setString(9, dniSearchProperty.getValue());

                //Ejecuta la consulta
                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Midificación Realizada");
                alert.setHeaderText("Se ha realizado correctamente la modificación de los datos.");
                alert.showAndWait();

                nombreTextField.setText("");
                apellidoTextField.setText("");
                dniTextField.setText("");
                correoTextField.setText("");
                fechaNacimientoProperty.setValue(null);
                telefonoProperty.setValue(null);
                nussProperty.setValue(null);
                cursoProperty.setValue(null);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGalc().getRoot());
    }

}
