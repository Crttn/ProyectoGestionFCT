package es.crttn.dad.controllers.secondary.tutores.empresa;

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

public class EliminarTutorEmpresaController implements Initializable {

    @FXML
    private TextField idTutotEmpresaTextField;

    @FXML
    private BorderPane root;

    private StringProperty idTutorEmpresaProperty = new SimpleStringProperty();

    public EliminarTutorEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/tutores/empresa/EliminarTutorEmprView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTutotEmpresaTextField.textProperty().bindBidirectional(idTutorEmpresaProperty);
    }

    public BorderPane getRoot() {
        return root;
    }



    @FXML
    void onDeleteAction(ActionEvent event) {
        if (idTutotEmpresaTextField != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Eliminar Tutor de Empresa");
            deleteAlert.setHeaderText("¿Estás seguro de que deseas eliminar el tutor de emrpresa con id: " + idTutorEmpresaProperty.getValue() +  "?");
            Optional<ButtonType> buttonType = deleteAlert.showAndWait();

            if(buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
                String querry = "DELETE FROM tutorempresa WHERE id_tutor_empresa = ?";

                try (Connection connection = DatabaseManager.getDataSource().getConnection();
                     PreparedStatement statement = connection.prepareStatement(querry)) {

                    // Usar los valores obtenidos de las propiedades
                    statement.setString(1, idTutorEmpresaProperty.getValue());

                    int filasAfectadas = statement.executeUpdate();
                    System.out.println("Tutor de Empresa eliminado : " + filasAfectadas + " fila(s) afectada(s)");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                idTutorEmpresaProperty.setValue("");
            }
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGtc().getGtec().getRoot());
    }
}
