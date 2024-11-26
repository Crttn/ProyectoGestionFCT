package es.crttn.dad.controllers.secondary.asignacion;

import es.crttn.dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EliminarAsignacionController implements Initializable {

    @FXML
    private TextField AsignacionTextfield;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private BorderPane root;

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

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {

    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGasc().getRoot());
    }


}
