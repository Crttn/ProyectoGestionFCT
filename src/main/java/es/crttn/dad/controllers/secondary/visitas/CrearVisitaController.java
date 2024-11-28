package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class CrearVisitaController implements Initializable {

    @FXML
    private TextField idpracticaTextfield;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea observacionesArea;

    @FXML
    private BorderPane root;

    @FXML
    private DatePicker visitaFecha;

    private final ObjectProperty<LocalDate> fechaProperty = new SimpleObjectProperty<>();
    private final StringProperty observacionesProperty = new SimpleStringProperty();
    private final IntegerProperty idpracticaProperty = new SimpleIntegerProperty();

    public CrearVisitaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/visitasSeguimiento/CrearVisitas.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idpracticaTextfield.textProperty().bindBidirectional(idpracticaProperty, new NumberStringConverter());
        observacionesArea.textProperty().bindBidirectional(observacionesProperty);
        visitaFecha.valueProperty().bindBidirectional(fechaProperty);

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

        String query = "INSERT INTO visitaseguimiento (id_practica, fecha, observaciones) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Validar datos antes de insertar
            if (idpracticaProperty.getValue() == null || fechaProperty.getValue() == null || observacionesProperty.getValue() == null) {
                System.out.println("Error: Todos los campos son obligatorios.");
                return;
            }

            // Preparar valores para la consulta
            statement.setInt(1, idpracticaProperty.getValue());
            statement.setDate(2, Date.valueOf(fechaProperty.getValue())); // Convertir java.util.Date a java.sql.Date
            statement.setString(3, observacionesProperty.getValue());

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();
            System.out.println("Visita insertada: " + filasAfectadas + " fila(s) afectada(s)");

            // Limpiar los campos de la interfaz
            idpracticaTextfield.setText("");
            visitaFecha.setValue(null); // Restablece el DatePicker
            observacionesArea.setText("");
            fechaProperty.setValue(null); // Restablece fechaProperty

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }

    public boolean validarCampos() {

        //ID Practica
        if (idpracticaTextfield.getText() == null || idpracticaTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Practica");
            alert.setHeaderText("El ID Practica no es correcto.");
            alert.showAndWait();
            return false;
        }

        //Verificar si la empresa existe en la base de datos

        String idPracticaStr = idpracticaTextfield.getText();
        try {
            int idPractica = Integer.parseInt(idPracticaStr);
            if (!existeIdPracticaEnBaseDeDatos(idPractica)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en ID Practica");
                alert.setHeaderText("El ID Practica no coincide con ningún registro en la base de datos.");
                alert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en ID Practica");
            alert.setHeaderText("El ID Practica debe ser un número entero.");
            alert.showAndWait();
            return false;
        }

        //Fecha
        if (visitaFecha.getValue() == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en Fecha");
            alert.setHeaderText("La fecha no puede ser nula.");
            alert.showAndWait();
            return false;
        }

        //Observaciones
        if (observacionesArea.getText() == null | observacionesArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en observaciones");
            alert.setHeaderText("Observaciones no puede ser nulo.");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    //VERIFICACIONES
    private boolean existeIdPracticaEnBaseDeDatos(int idPractica) {
        String query = "SELECT COUNT(*) FROM visitaseguimiento WHERE id_practica = ?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idPractica);
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
