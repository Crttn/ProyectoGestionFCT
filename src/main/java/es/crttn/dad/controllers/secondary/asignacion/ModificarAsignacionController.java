package es.crttn.dad.controllers.secondary.asignacion;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModificarAsignacionController implements Initializable {

    @FXML
    private DatePicker fechafinDatePicker;

    @FXML
    private DatePicker fechainicioDatePicker;

    @FXML
    private TextField idAlumnoTextfield;

    @FXML
    private TextField idEmpresaTextfield;

    @FXML
    private TextField idPracticaTextField;

    @FXML
    private TextField idTutorDocenteTextfield;

    @FXML
    private TextField idTutorEmpresaTextfield;

    @FXML
    private BorderPane root;

    private IntegerProperty practicaProperty = new SimpleIntegerProperty();
    private final IntegerProperty alumnoProperty = new SimpleIntegerProperty();
    private final IntegerProperty empresaProperty = new SimpleIntegerProperty();
    private final IntegerProperty tutorDocenteProperty = new SimpleIntegerProperty();
    private final IntegerProperty tutorEmpresaProperty = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> fechaInicioProperty = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> fechaFinProperty =new SimpleObjectProperty<>();


    public ModificarAsignacionController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/asignacionpracticas/ModificarAsigna.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idPracticaTextField.textProperty().bindBidirectional(practicaProperty, new NumberStringConverter());
        idPracticaTextField.setText("");
        idPracticaTextField.setFocusTraversable(false);

        idAlumnoTextfield.textProperty().bindBidirectional(alumnoProperty, new NumberStringConverter());
        idAlumnoTextfield.setText("");
        idEmpresaTextfield.textProperty().bindBidirectional(empresaProperty, new NumberStringConverter());
        idEmpresaTextfield.setText("");
        idTutorDocenteTextfield.textProperty().bindBidirectional(tutorDocenteProperty, new NumberStringConverter());
        idTutorDocenteTextfield.setText("");
        idTutorEmpresaTextfield.textProperty().bindBidirectional(tutorEmpresaProperty, new NumberStringConverter());
        idTutorEmpresaTextfield.setText("");
        fechainicioDatePicker.valueProperty().bindBidirectional(fechaInicioProperty);
        fechafinDatePicker.valueProperty().bindBidirectional(fechaFinProperty);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        String querry = "SELECT * FROM practica WHERE id_practica = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            statement.setString(1, practicaProperty.getValue().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    alumnoProperty.setValue(resultSet.getInt("id_practica"));
                    empresaProperty.setValue(resultSet.getInt("id_empresa"));
                    tutorDocenteProperty.setValue(resultSet.getInt("id_tutor_docente"));
                    tutorEmpresaProperty.setValue(resultSet.getInt("id_tutor_empresa"));
                    fechaInicioProperty.setValue(LocalDate.parse(resultSet.getString("fecha_inicio")));
                    fechaFinProperty.setValue(LocalDate.parse(resultSet.getString("fecha_fin")));



                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {

        // Validar los campos antes de continuar
        if (!validarCampos()) {
            return;
        }

        String querry = "UPDATE practica SET id_alumno = ?, id_empresa = ?, id_tutor_docente = ?, id_tutor_empresa = ?, fecha_inicio = ?, fecha_fin = ?";

        if (alumnoProperty.getValue() != null && empresaProperty.getValue() != null && tutorDocenteProperty.getValue() != null &&
                tutorEmpresaProperty.getValue() != null && fechaInicioProperty.getValue() != null && fechaFinProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setInt(1, alumnoProperty.getValue());
                statement.setInt(2, empresaProperty.getValue());
                statement.setInt(3, tutorDocenteProperty.getValue());
                statement.setInt(4, tutorEmpresaProperty.getValue());
                statement.setString(5, String.valueOf(fechaInicioProperty.getValue()));
                statement.setString(6, String.valueOf(fechaFinProperty.getValue()));

                //Ejecuta la consulta
                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modificación Realizada");
                alert.setHeaderText("Se ha realizado correctamente la modificación de los datos.");
                alert.showAndWait();

                // Limpiar campos
                practicaProperty.setValue(null);
                alumnoProperty.setValue(null);
                empresaProperty.setValue(null);
                tutorDocenteProperty.setValue(null);
                tutorEmpresaProperty.setValue(null);
                fechaInicioProperty.setValue(null);
                fechaFinProperty.setValue(null);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGasc().getRoot());
    }

    //VERIFICACIONES//

    public boolean validarCampos() {

        // ID Alumno
        if (idAlumnoTextfield.getText() == null || idAlumnoTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Alumno");
            alert.setHeaderText("El ID Alumno no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        // Verificar que el ID Alumno existe en la base de datos
        String idAlumnoStr = idAlumnoTextfield.getText();
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
        if (idEmpresaTextfield.getText() == null || idEmpresaTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Empresa");
            alert.setHeaderText("El ID Empresa no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        String idEmpresaStr = idEmpresaTextfield.getText();
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
        if (idTutorDocenteTextfield.getText() == null || idTutorDocenteTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Docente");
            alert.setHeaderText("El ID Docente no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        String idDocenteStr = idTutorDocenteTextfield.getText();
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
        if (idTutorEmpresaTextfield.getText() == null || idTutorEmpresaTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Tutor Empresa");
            alert.setHeaderText("El ID Tutor Empresa no debe estar vacío.");
            alert.showAndWait();
            return false;
        }

        String idTutorEmpresaStr = idTutorEmpresaTextfield.getText();
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
        if (fechainicioDatePicker.getValue() == null || fechainicioDatePicker.getValue().isAfter(java.time.LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha Inicio");
            alert.setHeaderText("La fecha no debe ser nula ni en el futuro.");
            alert.showAndWait();
            return false;
        }

        // Fecha Fin
        if (fechafinDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha Fin");
            alert.setHeaderText("La fecha no debe ser nula.");
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
        String query = "SELECT COUNT(*) FROM tutordocente WHERE id_docente = ?";
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
