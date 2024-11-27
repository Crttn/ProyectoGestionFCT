package es.crttn.dad.controllers.secondary.asignacion;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;

public class EliminarAsignacionController implements Initializable {

    @FXML
    private TextField idPracticaTextField;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private BorderPane root;

    private IntegerProperty practicaProperty = new SimpleIntegerProperty();

    public EliminarAsignacionController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/asignacionpracticas/EliminarAsignacionView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idPracticaTextField.textProperty().bindBidirectional(practicaProperty, new NumberStringConverter());
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
        if (practicaProperty != null) {

            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Eliminar Práctica");
            deleteAlert.setHeaderText("¿Estás seguro de que deseas eliminar la práctica: " + practicaProperty.getValue() +  "?");
            Optional<ButtonType> buttonType = deleteAlert.showAndWait();

            if(buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
                String querry = "DELETE FROM practica WHERE id_practica = ?";

                try (Connection connection = DatabaseManager.getDataSource().getConnection();
                     PreparedStatement statement = connection.prepareStatement(querry)) {

                    // Usar los valores obtenidos de las propiedades
                    statement.setString(1, practicaProperty.getValue().toString());

                    int filasAfectadas = statement.executeUpdate();
                    System.out.println("Práctica eliminada: " + filasAfectadas + " fila(s) afectada(s)");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                practicaProperty.setValue(null);
            }
        }
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGasc().getRoot());
    }


}
