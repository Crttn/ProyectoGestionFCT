package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
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


        visitaFecha.valueProperty().bindBidirectional(fechaProperty);
        observacionesArea.textProperty().bindBidirectional(observacionesProperty);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {

        String querry = "INSERT INTO visitaseguimiento (id_practica, fecha, observaciones) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setInt(1, idpracticaProperty.getValue());
            statement.setDate(2, Date.valueOf(fechaProperty.getValue()));
            statement.setString(3,observacionesProperty.getValue());


            int filasAfectadas = statement.executeUpdate();
            System.out.println("Visita insertada: " + filasAfectadas + " fila(s) afectada(s)");

            idpracticaTextfield.setText("");
            fechaProperty.setValue(LocalDate.parse(""));
            observacionesArea.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
