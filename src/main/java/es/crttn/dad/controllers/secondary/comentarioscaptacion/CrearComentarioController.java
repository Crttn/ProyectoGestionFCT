package es.crttn.dad.controllers.secondary.comentarioscaptacion;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
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

public class CrearComentarioController implements Initializable {

    @FXML
    private TextArea comentarioTextArea;

    @FXML
    private DatePicker fechaTextField;

    @FXML
    private TextField idEmpresaTextField;

    @FXML
    private BorderPane root;

    private ObjectProperty<LocalDate> fechaProperty = new SimpleObjectProperty<>();
    private StringProperty comentarioProperty = new SimpleStringProperty();
    private IntegerProperty empresaProperty = new SimpleIntegerProperty();

    public CrearComentarioController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/comentarioscaptacion/CrearComentarioCaptacion.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fechaTextField.valueProperty().bindBidirectional(fechaProperty);
        comentarioTextArea.textProperty().bindBidirectional(comentarioProperty);
        idEmpresaTextField.textProperty().bindBidirectional(empresaProperty, new NumberStringConverter());
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddAction(ActionEvent event) {
        String query = "INSERT INTO comnetariocaptacion (fecha, comentario, id_empresa) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Validar datos antes de insertar
            if (fechaProperty.getValue() != null && comentarioProperty.getValue() != null && empresaProperty.getValue() != null) {

                // Preparar valores para la consulta
                statement.setDate(1, Date.valueOf(fechaProperty.getValue()));
                statement.setString(2, comentarioProperty.getValue()); // Convertir java.util.Date a java.sql.Date
                statement.setInt(3, empresaProperty.getValue());

                // Ejecutar la consulta
                int filasAfectadas = statement.executeUpdate();
                System.out.println("Comentario insertado: " + filasAfectadas + " fila(s) afectada(s)");

                // Limpiar los campos de la interfaz
                fechaProperty.setValue(null);
                comentarioProperty.setValue(null);
                empresaProperty.setValue(null);
                idEmpresaTextField.setText("");

            } else {
                System.out.println("Error: Todos los campos son obligatorios.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGcc().getRoot());
    }
}
