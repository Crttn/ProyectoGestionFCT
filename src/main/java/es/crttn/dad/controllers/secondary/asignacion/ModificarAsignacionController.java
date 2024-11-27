package es.crttn.dad.controllers.secondary.asignacion;

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
import java.util.ResourceBundle;

public class ModificarAsignacionController implements Initializable {

    @FXML
    private TextField fechaFinTextfield;

    @FXML
    private TextField fechaInicioTextfield;

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
    private final StringProperty fechaInicioProperty = new SimpleStringProperty();
    private final StringProperty fechaFinProperty = new SimpleStringProperty();


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
        idAlumnoTextfield.textProperty().bindBidirectional(alumnoProperty, new NumberStringConverter());
        idEmpresaTextfield.textProperty().bindBidirectional(empresaProperty, new NumberStringConverter());
        idTutorDocenteTextfield.textProperty().bindBidirectional(tutorDocenteProperty, new NumberStringConverter());
        idTutorEmpresaTextfield.textProperty().bindBidirectional(tutorEmpresaProperty, new NumberStringConverter());
        fechaInicioTextfield.textProperty().bindBidirectional(fechaInicioProperty);
        fechaFinTextfield.textProperty().bindBidirectional(fechaFinProperty);
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
                    fechaInicioTextfield.setText(String.valueOf(resultSet.getDate("fecha_inicio")));
                    fechaFinTextfield.setText(String.valueOf(resultSet.getDate("fecha_fin")));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {
        String querry = "UPDATE practica SET id_alumno = ?, id_empresa = ?, id_tutor_docente = ?, id_tutor_empresa = ?, fecha_inicio = ?, fecha_fin = ?";

        if (alumnoProperty.getValue() != null && empresaProperty.getValue() != null && tutorDocenteProperty.getValue() != null &&
                tutorEmpresaProperty.getValue() != null && fechaInicioProperty.getValue() != null && fechaFinProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setInt(1, alumnoProperty.getValue());
                statement.setInt(2, empresaProperty.getValue());
                statement.setInt(3, tutorDocenteProperty.getValue());
                statement.setInt(4, tutorEmpresaProperty.getValue());
                statement.setString(5, fechaInicioProperty.getValue());
                statement.setString(6, fechaFinProperty.getValue());

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
}
