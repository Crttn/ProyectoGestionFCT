package es.crttn.dad.controllers.secondary.asignacion;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class CrearAsignacionController implements Initializable {

    @FXML
    private DatePicker fechaFinDatePicker;

    @FXML
    private DatePicker fechaInicioDatePicker;

    @FXML
    private TextField idAlumnoTextField;

    @FXML
    private TextField idDocenteTextField;

    @FXML
    private TextField idEmpresaTextField;

    @FXML
    private TextField idTutoEmpresaTextFielld;

    @FXML
    private BorderPane root;

    private IntegerProperty alumnoProperty = new SimpleIntegerProperty();
    private IntegerProperty empresaProperty = new SimpleIntegerProperty();
    private IntegerProperty tutorDocenteProperty = new SimpleIntegerProperty();
    private IntegerProperty tutorEmpresaProperty = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> fechaInicioProperty = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> fechaFinProperty = new SimpleObjectProperty<>();

    public CrearAsignacionController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/asignacionpracticas/CrearAsignacion.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idAlumnoTextField.textProperty().bindBidirectional(alumnoProperty, new NumberStringConverter());
        idEmpresaTextField.textProperty().bindBidirectional(empresaProperty, new NumberStringConverter());
        idDocenteTextField.textProperty().bindBidirectional(tutorDocenteProperty, new NumberStringConverter());
        idTutoEmpresaTextFielld.textProperty().bindBidirectional(tutorEmpresaProperty, new NumberStringConverter());
        fechaInicioDatePicker.valueProperty().bindBidirectional(fechaInicioProperty);
        fechaFinDatePicker.valueProperty().bindBidirectional(fechaFinProperty);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {
        String query = "INSERT INTO practica (id_alumno, id_empresa, id_tutor_docente, id_tutor_empresa, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Validar datos antes de insertar
            if (alumnoProperty.getValue() != null && empresaProperty.getValue() != null && tutorDocenteProperty.getValue() != null &&
                tutorEmpresaProperty.getValue() != null && fechaInicioProperty.getValue() != null && fechaFinProperty.getValue() != null) {

                // Preparar valores para la consulta
                statement.setInt(1, alumnoProperty.getValue());
                statement.setInt(2, empresaProperty.getValue()); // Convertir java.util.Date a java.sql.Date
                statement.setInt(3, tutorDocenteProperty.getValue());
                statement.setInt(4, tutorEmpresaProperty.getValue());
                statement.setDate(5, Date.valueOf(fechaInicioProperty.getValue()));
                statement.setDate(6, Date.valueOf(fechaFinProperty.getValue()));

                // Ejecutar la consulta
                int filasAfectadas = statement.executeUpdate();
                System.out.println("Práctica insertada: " + filasAfectadas + " fila(s) afectada(s)");

                // Limpiar los campos de la interfaz
                idAlumnoTextField.setText("");
                idEmpresaTextField.setText("");
                idDocenteTextField.setText("");
                idEmpresaTextField.setText("");
                fechaInicioDatePicker.setValue(null);
                fechaFinProperty.setValue(null);

            } else {
                System.out.println("Error: Todos los campos son obligatorios.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGasc().getRoot());
    }
}
