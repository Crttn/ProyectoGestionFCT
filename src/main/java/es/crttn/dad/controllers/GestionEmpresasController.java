package es.crttn.dad.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionEmpresasController implements Initializable {

    //View

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button findButton;

    @FXML
    private BorderPane root;

    @FXML
    private Button updateButton;

    @FXML
    void onAddButton(ActionEvent event) {

    }

    public GestionEmpresasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionEmpresasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onDeleteButton(ActionEvent event) {

    }

    @FXML
    void onFindButton(ActionEvent event) {

    }

    @FXML
    void onUpdateButton(ActionEvent event) {

    }
}
