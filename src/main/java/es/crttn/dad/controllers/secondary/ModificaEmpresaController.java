package es.crttn.dad.controllers.secondary;

import es.crttn.dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ModificaEmpresaController implements Initializable {


    @FXML
    private TextField nombreEmpresaTextField;

    @FXML
    private BorderPane root;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onModifyAction(ActionEvent event) {

    }

    @FXML
    void onBackaction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());
    }
}
