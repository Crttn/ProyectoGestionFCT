package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private final ObjectProperty<Date> fechaProperty = new SimpleObjectProperty<>();
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

        visitaFecha.valueProperty().addListener((obs, oldv, newv) -> {
            if (newv != null) {
                fechaProperty.set(Date.from(newv.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            } else {
                fechaProperty.set(null);
            }
        });

        fechaProperty.addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                visitaFecha.setValue(newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            } else {
                visitaFecha.setValue(null);
            }
        });

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {
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
            statement.setDate(2, new java.sql.Date(fechaProperty.getValue().getTime())); // Convertir java.util.Date a java.sql.Date
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
}
