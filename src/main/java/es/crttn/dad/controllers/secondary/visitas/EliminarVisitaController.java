package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;

public class EliminarVisitaController implements Initializable {

    @FXML
    private TextField idvisitaTextfield;

    @FXML
    private BorderPane root;

    private StringProperty visitaProperty = new SimpleStringProperty();

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

        idvisitaTextfield.textProperty().bindBidirectional(visitaProperty);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

        if (visitaProperty != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Eliminar Visita");
            deleteAlert.setHeaderText("¿Estás seguro de que deseas eliminar la visita " + visitaProperty.getValue() +  "?");
            Optional<ButtonType> buttonType = deleteAlert.showAndWait();

            if(buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
                String querry = "DELETE FROM visitaseguimiento WHERE id_visita = ?";

                try (Connection connection = DatabaseManager.getDataSource().getConnection();
                     PreparedStatement statement = connection.prepareStatement(querry)) {

                    // Usar los valores obtenidos de las propiedades
                    statement.setString(1, visitaProperty.getValue());

                    int filasAfectadas = statement.executeUpdate();
                    System.out.println("Visita eliminada: " + filasAfectadas + " fila(s) afectada(s)");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                visitaProperty.setValue("");
            }
        }

    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
