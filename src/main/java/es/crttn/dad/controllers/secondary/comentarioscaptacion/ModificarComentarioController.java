package es.crttn.dad.controllers.secondary.comentarioscaptacion;

import es.crttn.dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModificarComentarioController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TextArea comentarioArea;

    @FXML
    private DatePicker comentarioFecha;

    @FXML
    private Button findButton;

    @FXML
    private TextField comentarioTextfield;

    @FXML
    private BorderPane root;

    public ModificarComentarioController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/comentarioscaptacion/ModificarComentario.fxml"));
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
    void onApplyAction(ActionEvent event) {

    }

    @FXML
    void onSearchAction(ActionEvent event) {

    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGcc().getRoot());
    }

}
