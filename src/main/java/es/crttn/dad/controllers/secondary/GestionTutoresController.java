package es.crttn.dad.controllers.secondary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.primary.GestionDocenteController;
import es.crttn.dad.controllers.primary.GestionTutorEmpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionTutoresController implements Initializable {

    GestionDocenteController gdc = new GestionDocenteController();
    GestionTutorEmpController gtec = new GestionTutorEmpController();

    @FXML
    private BorderPane root;

    public GestionTutoresController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionTutoresView.fxml"));
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
    void onTutoDocenteAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(gdc.getRoot());
    }

    @FXML
    void onTutorEmpresaAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(gtec.getRoot());
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getRoot());
    }

    public GestionDocenteController getGdc() {
        return gdc;
    }

    public GestionTutorEmpController getGtec() {
        return gtec;
    }
}
