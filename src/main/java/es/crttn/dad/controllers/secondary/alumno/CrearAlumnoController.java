package es.crttn.dad.controllers.secondary.alumno;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CrearAlumnoController implements Initializable {

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField cursoTextField;

    @FXML
    private TextField dniTextField;

    @FXML
    private DatePicker fechaNacimientoTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField nussTextField;

    @FXML
    private BorderPane root;

    @FXML
    private TextField telefonoTextField;

    private StringProperty nombreProperty = new SimpleStringProperty();
    private StringProperty appellidoProperty = new SimpleStringProperty();
    private StringProperty correoProperty = new SimpleStringProperty();
    private StringProperty dniProperty = new SimpleStringProperty();
    private ObjectProperty<LocalDate> fechaNacimientoProperty = new SimpleObjectProperty<>();
    private IntegerProperty telefonoProperty = new SimpleIntegerProperty();
    private IntegerProperty cursoProperty = new SimpleIntegerProperty();
    private IntegerProperty nussProperty = new SimpleIntegerProperty();

    public CrearAlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/alumno/CrearAlumnoView.fxml"));
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
        dniTextField.textProperty().bindBidirectional(dniProperty);
        fechaNacimientoTextField.valueProperty().bindBidirectional(fechaNacimientoProperty);
        telefonoTextField.textProperty().bindBidirectional(telefonoProperty, new NumberStringConverter());
        cursoTextField.textProperty().bindBidirectional(cursoProperty, new NumberStringConverter());
        nussTextField.textProperty().bindBidirectional(nussProperty, new NumberStringConverter());
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

        String querry = "INSERT INTO alumno (nombre, apellido, dni, correo, fecha_nacimiento, telefono, codigo_nuss, id_curso) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, nombreProperty.getValue());
            statement.setString(2, appellidoProperty.getValue());
            statement.setString(3, dniProperty.getValue());
            statement.setString(4, correoProperty.getValue());
            statement.setDate(5, Date.valueOf(fechaNacimientoProperty.getValue()));
            statement.setInt(6, telefonoProperty.getValue());
            statement.setInt(7, nussProperty.getValue());
            statement.setInt(8, cursoProperty.getValue());

            int filasAfectadas = statement.executeUpdate();
            System.out.println("Alumno creado: " + filasAfectadas + " fila(s) afectada(s)");

            nombreTextField.setText("");
            apellidoTextField.setText("");
            dniTextField.setText("");
            correoTextField.setText("");
            fechaNacimientoTextField.setValue(null);
            telefonoTextField.setText("");
            nussTextField.setText("");
            cursoTextField.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCancelAction(ActionEvent event) {
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

        //DNI
        if (dniTextField.getText()== null || !dniTextField.getText().matches("\\d{8}[A-Za-z]")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en DNI");
            alert.setHeaderText("El DNI debe contener exactamente 8 dígitos y una letra");
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
        if (fechaNacimientoTextField.getValue()== null || fechaNacimientoTextField.getValue().isAfter(java.time.LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha Nacimiento");
            alert.setHeaderText("La fecha de nacimiento no debe ser nula ni estar en el futuro.");
            alert.showAndWait();
            return false;
        }

        //Nuss
        if (nussTextField.getText()== null || !nussTextField.getText().matches("\\d{10}")) {
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
}
