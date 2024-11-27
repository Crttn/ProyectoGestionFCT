package es.crttn.dad.controllers.secondary.visitas;

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

public class EliminarVisitaController implements Initializable {

    @FXML
    private TextField idVisitaTextField;

    @FXML
    private BorderPane root;

    private IntegerProperty idVisitaProperty = new SimpleIntegerProperty();

    public EliminarVisitaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/visitasSeguimiento/EliminarVisitaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idVisitaTextField.textProperty().bindBidirectional(idVisitaProperty, new NumberStringConverter());

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

        if (idVisitaProperty != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Eliminar Visita");
            deleteAlert.setHeaderText("¿Estás seguro de que deseas eliminar la visita " + idVisitaProperty.getValue() +  "?");
            Optional<ButtonType> buttonType = deleteAlert.showAndWait();

            if(buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
                String querry = "DELETE FROM visitaseguimiento WHERE id_visita = ?";

                try (Connection connection = DatabaseManager.getDataSource().getConnection();
                     PreparedStatement statement = connection.prepareStatement(querry)) {

                    // Usar los valores obtenidos de las propiedades
                    statement.setString(1, idVisitaProperty.getValue().toString());

                    int filasAfectadas = statement.executeUpdate();
                    System.out.println("Visita eliminada: " + filasAfectadas + " fila(s) afectada(s)");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                idVisitaProperty.setValue(0);
            }
        }

    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
