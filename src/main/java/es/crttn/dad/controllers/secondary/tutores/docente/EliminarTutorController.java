package es.crttn.dad.controllers.secondary.tutores.docente;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;

public class EliminarTutorController implements Initializable {

    @FXML
    private TextField idDocenteTextField;

    @FXML
    private BorderPane root;

    private StringProperty idDocenteProperty = new SimpleStringProperty();

    public EliminarTutorController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/tutores/docente/EliminarDocenteView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idDocenteTextField.textProperty().bindBidirectional(idDocenteProperty);
    }

    public BorderPane getRoot() {
        return root;
    }



    @FXML
    void onDeleteAction(ActionEvent event) {
        if (idDocenteTextField != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Eliminar Docente");
            deleteAlert.setHeaderText("¿Estás seguro de que deseas eliminar el docente con id: " + idDocenteProperty.getValue() +  "?");
            Optional<ButtonType> buttonType = deleteAlert.showAndWait();

            if(buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
                String querry = "DELETE FROM tutordocente WHERE id_tutor_docente = ?";

                try (Connection connection = DatabaseManager.getDataSource().getConnection();
                     PreparedStatement statement = connection.prepareStatement(querry)) {

                    // Usar los valores obtenidos de las propiedades
                    statement.setString(1, idDocenteProperty.getValue());

                    int filasAfectadas = statement.executeUpdate();
                    System.out.println("Docente eliminado : " + filasAfectadas + " fila(s) afectada(s)");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                idDocenteProperty.setValue("");
            }
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGtc().getGdc().getRoot());
    }
}
