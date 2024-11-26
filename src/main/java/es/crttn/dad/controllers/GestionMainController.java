package es.crttn.dad.controllers;

import es.crttn.dad.App;
import es.crttn.dad.controllers.primary.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionMainController implements Initializable {

    private GestionEmpresasController gec = new GestionEmpresasController();
    private GestionAlumnosController galc = new GestionAlumnosController();
    private GestionVisitasController gvc = new GestionVisitasController();
    private  GestionAsignacionesController gasc = new GestionAsignacionesController();
    private GestionComentariosController gcc = new GestionComentariosController();

    @FXML
    private BorderPane root;

    public GestionMainController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionMainView.fxml"));
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
    void onGestionEmpresasAction(ActionEvent event) throws IOException {
        App.getRootController().getRoot().setCenter(gec.getRoot());
    }

    @FXML
    void onGestionAlumnosAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(galc.getRoot());
    }

    @FXML
    void onAsignacionPracticasAction(ActionEvent event) throws IOException {
        App.getRootController().getRoot().setCenter(gasc.getRoot());
    }

    @FXML
    void onGestionVisitasAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(gvc.getRoot());
    }

    @FXML
    void onGestionComentariosAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(gcc.getRoot());
    }

    public GestionEmpresasController getGec() {
        return gec;
    }

    public GestionAlumnosController getGalc() {
        return galc;
    }

    public GestionComentariosController getGcc() {
        return gcc;
    }

    public GestionAsignacionesController getGasc() {
        return gasc;
    }

    public GestionVisitasController getGvc() {
        return gvc;
    }
}
