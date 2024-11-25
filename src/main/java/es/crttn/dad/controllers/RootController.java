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
        GestionEmpresasController gestionEmpresasController = new GestionEmpresasController();
        stage.setTitle("Asiganción de prácticas");
        stage.setScene(new Scene(gestionEmpresasController.getRoot()));
        stage.show();

    }

    @FXML
    void onGestionAlumnosAction(ActionEvent event) {
        
        Stage stage = new Stage();
        GestionEmpresasController gestionEmpresasController = new GestionEmpresasController();
        stage.setTitle("Gestión de alumnos");
        stage.setScene(new Scene(gestionEmpresasController.getRoot()));
        stage.show();

    }

    @FXML
    void onGestionComentariosAction(ActionEvent event) {

        GestionComentariosController gestionComentariosController = new GestionComentariosController();

        Stage stage = new Stage();
        GestionEmpresasController gestionEmpresasController = new GestionEmpresasController();
        stage.setTitle("Gestión de comentarios");
        stage.setScene(new Scene(gestionComentariosController.getRoot()));
        stage.show();

    }

    @FXML
    void onGestionEmpresasAction(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        GestionEmpresasController gestionEmpresasController = new GestionEmpresasController();
        stage.setTitle("Gestión de empresas");
        stage.setScene(new Scene(gestionEmpresasController.getRoot()));
        stage.show();

    }

    @FXML
    void onGestionVisitasAction(ActionEvent event) {

        Stage stage = new Stage();
        GestionEmpresasController gestionEmpresasController = new GestionEmpresasController();
        stage.setTitle("Gestión de Visitas");
        stage.setScene(new Scene(gestionEmpresasController.getRoot()));
        stage.show();

    }
}
