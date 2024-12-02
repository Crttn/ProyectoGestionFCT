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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

        idAlumnoTextField.setText("");
        idEmpresaTextField.setText("");
        idDocenteTextField.setText("");
        idTutoEmpresaTextFielld.setText("");
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {

        // Validar los campos antes de continuar
        if (!validarCampos()) {
            return;
        }

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
                idTutoEmpresaTextFielld.setText("");
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
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGasc().getRoot());
    }

    //VERIFICACIONES//

    public boolean validarCampos() {

        // ID Alumno
        if (idAlumnoTextField.getText() == null || idAlumnoTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Alumno");
            alert.setHeaderText("El ID Alumno no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        // Verificar que el ID Alumno existe en la base de datos
        String idAlumnoStr = idAlumnoTextField.getText();
        try {
            int idAlumno = Integer.parseInt(idAlumnoStr);
            if (!existeIdAlumnoEnBaseDeDatos(idAlumno)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en ID Alumno");
                alert.setHeaderText("El ID Alumno no coincide con ningún registro en la base de datos.");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Alumno");
            alert.setHeaderText("El ID Alumno debe ser un número entero.");
            alert.showAndWait();
            return false;
        }

        // ID Empresa
        if (idEmpresaTextField.getText() == null || idEmpresaTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Empresa");
            alert.setHeaderText("El ID Empresa no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        String idEmpresaStr = idEmpresaTextField.getText();
        try {
            int idEmpresa = Integer.parseInt(idEmpresaStr);
            if (!existeIdEmpresaEnBaseDeDatos(idEmpresa)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en ID Empresa");
                alert.setHeaderText("El ID Empresa no coincide con ningún registro en la base de datos.");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Empresa");
            alert.setHeaderText("El ID Empresa debe ser un número entero.");
            alert.showAndWait();
            return false;
        }

        // ID Docente
        if (idDocenteTextField.getText() == null || idDocenteTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Docente");
            alert.setHeaderText("El ID Docente no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        String idDocenteStr = idDocenteTextField.getText();
        try {
            int idDocente = Integer.parseInt(idDocenteStr);
            if (!existeIdDocenteEnBaseDeDatos(idDocente)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en ID Docente");
                alert.setHeaderText("El ID Docente no coincide con ningún registro en la base de datos.");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Docente");
            alert.setHeaderText("El ID Docente debe ser un número entero.");
            alert.showAndWait();
            return false;
        }

        // ID Tutor Empresa
        if (idTutoEmpresaTextFielld.getText() == null || idTutoEmpresaTextFielld.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Tutor Empresa");
            alert.setHeaderText("El ID Tutor Empresa no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        String idTutorEmpresaStr = idTutoEmpresaTextFielld.getText();
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

        // Fecha Inicio
        if (fechaInicioDatePicker.getValue() == null || fechaInicioDatePicker.getValue().isAfter(java.time.LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha Inicio");
            alert.setHeaderText("La fecha no debe ser nula ni en el futuro.");
            alert.showAndWait();
            return false;
        }

        // Fecha Fin
        if (fechaFinDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha Fin");
            alert.setHeaderText("La fecha no debe ser nula.");
            alert.showAndWait();
            return false;
        }

        // Verificar si fecha fin es inferior a fecha inicio
        if (fechaInicioDatePicker.getValue() != null && fechaFinDatePicker.getValue().isBefore(fechaInicioDatePicker.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha Fin");
            alert.setHeaderText("La fecha de fin no puede ser anterior a la fecha de inicio.");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    //Verificar si el alumno existe en la base

    private boolean existeIdAlumnoEnBaseDeDatos(int idAlumno) {
        String query = "SELECT COUNT(*) FROM alumno WHERE id_alumno = ?";
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

    //Verificar si el ID empresa existe en la base

    private boolean existeIdEmpresaEnBaseDeDatos(int idEmpresa) {
        String query = "SELECT COUNT(*) FROM empresa WHERE id_empresa = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idEmpresa);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Verificar si el id docente existe en la base

    private boolean existeIdDocenteEnBaseDeDatos(int idDocente) {
        String query = "SELECT COUNT(*) FROM tutordocente WHERE id_tutor_docente = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idDocente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Verificar si el id tutor existe en la base

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
