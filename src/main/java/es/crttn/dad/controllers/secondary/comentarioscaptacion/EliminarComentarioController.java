package es.crttn.dad.controllers.secondary.comentarioscaptacion;

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

public class EliminarComentarioController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TextField comentarioTextfield;

    @FXML
    private Button deleteButton;

    @FXML
    private BorderPane root;

    public EliminarComentarioController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/comentarioscaptacion/EliminarComentarioView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onBackButtonAction(ActionEvent event) {

    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {

    }
}
