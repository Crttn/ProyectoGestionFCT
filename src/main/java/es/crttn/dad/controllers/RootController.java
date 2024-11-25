package es.crttn.dad.controllers;

import es.crttn.dad.controllers.primary.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private BorderPane root;

    private GestionMainController gestionMainController;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gestionMainController = new GestionMainController();

        getRoot().setCenter(gestionMainController.getRoot());

    }

    public BorderPane getRoot() {
        return root;
    }

    public GestionMainController getGestionMainController() {
        return gestionMainController;
    }
}
