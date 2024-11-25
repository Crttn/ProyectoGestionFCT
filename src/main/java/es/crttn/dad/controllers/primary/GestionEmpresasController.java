package es.crttn.dad.controllers.primary;

import es.crttn.dad.controllers.secondary.CrearEmpresaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionEmpresasController implements Initializable {

    @FXML
    private BorderPane root;


    public GestionEmpresasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionEmpresasView.fxml"));
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
    void onAddButtonAction(ActionEvent event) {

        CrearEmpresaController cec = new CrearEmpresaController();

        Stage stage = new Stage();
        stage.setTitle("Crear Empresa");
        stage.setScene(new Scene(cec.getRoot()));
        stage.show();

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
