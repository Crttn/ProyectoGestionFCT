package es.crttn.dad.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private BorderPane root;


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


    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAsignacionPracticasAction(ActionEvent event) throws IOException {


        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml"));
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onGestionAlumnosAction(ActionEvent event) {

    }

    @FXML
    void onGestionComentariosAction(ActionEvent event) {

    }

    @FXML
    void onGestionEmpresasAction(ActionEvent event) {

    }

    @FXML
    void onGestionVisitasAction(ActionEvent event) {

    }
}
