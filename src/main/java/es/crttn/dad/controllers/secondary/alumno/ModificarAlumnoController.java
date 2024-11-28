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
import java.sql.*;
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

        // Validar los campos antes de continuar
        if (!validarCampos()) {
            return;
        }

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
                alert.setTitle("Modificación Realizada");
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

        // DNI
        if (dniTextField.getText() == null || !dniTextField.getText().matches("\\d{8}[A-Za-z]")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en DNI");
            alert.setHeaderText("El DNI debe contener exactamente 8 dígitos y una letra.");
            alert.showAndWait();
            return false;
        }

        String dniAlumnoStr = dniTextField.getText();

        // Verificamos si el DNI ya existe en la base de datos
        if (existeDINAlumnoEnBaseDeDatos(Integer.parseInt(dniAlumnoStr))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en DNI alumno");
            alert.setHeaderText("El DNI ya existe en la base de datos.");
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
        //Fecha Nac
        if (fechaNacimientoDatePicker.getValue()== null || fechaNacimientoDatePicker.getValue().isAfter(java.time.LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha Nacimiento");
            alert.setHeaderText("La fecha de nacimiento no debe ser nula ni estar en el futuro.");
            alert.showAndWait();
            return false;
        }

        //Nuss
        if (codigoNussTextField.getText()== null || !codigoNussTextField.getText().matches("\\d{10}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Codigo Nuss");
            alert.setHeaderText("El codigo nuss debe contener solo 10 digitos.");
            alert.showAndWait();
            return false;
        }

        // Curso: obligatorio, solo números
        if (cursoTextField.getText() == null || !cursoTextField.getText().matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Curso");
            alert.setHeaderText("El Curso debe ser numerico.");
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
