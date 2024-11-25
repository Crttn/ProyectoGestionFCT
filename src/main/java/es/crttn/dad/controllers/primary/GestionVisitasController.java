package es.crttn.dad.controllers.primary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionVisitasController implements Initializable {

    @FXML
    private BorderPane root;

    public GestionVisitasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionVisitasSeguimiento.fxml"));
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
    void onAddButtonAction(ActionEvent event) {

    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {

    }

    @FXML
    void onFindButtonAction(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {

    }
}