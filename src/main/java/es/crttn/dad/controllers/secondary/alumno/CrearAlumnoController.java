package es.crttn.dad.controllers.secondary.alumno;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
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
}
