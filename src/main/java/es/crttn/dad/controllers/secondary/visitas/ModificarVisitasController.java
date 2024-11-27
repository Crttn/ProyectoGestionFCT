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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModificarVisitasController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Button findButton;

    @FXML
    private TextField idVisitaTextField;

    @FXML
    private TextArea observacionesArea;

    @FXML
    private BorderPane root;

    @FXML
    private DatePicker visitaFecha;

    private final ObjectProperty<LocalDate> fechaProperty = new SimpleObjectProperty<>();
    private final StringProperty observacionesProperty = new SimpleStringProperty();
    private final IntegerProperty idProperty = new SimpleIntegerProperty();

    public ModificarVisitasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/visitasSeguimiento/ModificarVisita.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idVisitaTextField.textProperty().bindBidirectional(idProperty, new NumberStringConverter());
        visitaFecha.valueProperty().bindBidirectional(fechaProperty);
        observacionesArea.textProperty().bindBidirectional(observacionesProperty);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchButtonAction(ActionEvent event) {
        String querry = "SELECT fecha, observaciones FROM visitaseguimiento WHERE id_visita = ?";


        if (idProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setInt(1, idProperty.getValue());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        visitaFecha.setValue(resultSet.getDate("Fecha").toLocalDate());
                        observacionesArea.setText(resultSet.getString("Observaciones"));
                    }
                }

                visitaFecha.setValue(fechaProperty.getValue());
                observacionesArea.setText(observacionesProperty.getValue());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {

        String querry = "UPDATE visitaseguimiento SET fecha = ?, observaciones = ? WHERE id_visita = ?";

        if (idProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setInt(1, idProperty.getValue());
                statement.setDate(2, java.sql.Date.valueOf(fechaProperty.getValue()));
                statement.setString(3, observacionesProperty.getValue());

                //Ejecuta la consulta
                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modificación Realizada");
                alert.setHeaderText("Se ha realizado correctamente la modificación de los datos.");
                alert.showAndWait();

                // Limpiar campos
                visitaFecha.setValue(null);
                observacionesArea.setText("");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
