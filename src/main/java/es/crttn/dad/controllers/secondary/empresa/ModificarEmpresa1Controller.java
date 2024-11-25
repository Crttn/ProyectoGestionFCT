package es.crttn.dad.controllers.secondary.empresa;

import es.crttn.dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ModificarEmpresa1Controller implements Initializable {

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField direccionTextField;

    @FXML
    private TextField especialidadTextField;

    @FXML
    private TextField horarioTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField plazasDispTextField;

    @FXML
    private BorderPane root;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddAction(ActionEvent event) {
        
    }

    @FXML
    void onCancelAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getMec().getRoot());
    }
}
