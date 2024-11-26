package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscarVisitaController implements Initializable {

    @FXML
    private TableView<?> VisitasSeguimientoView;

    @FXML
    private TableColumn<?, ?> columnaidpractica;

    @FXML
    private TableColumn<?, ?> culumnaidvisita;

    @FXML
    private TableColumn<?, ?> fecha;

    @FXML
    private TextField idpracticaTextfield;

    @FXML
    private TableColumn<?, ?> observaciones;

    @FXML
    private BorderPane root;


    public BuscarVisitaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/visitasSeguimiento/BuscarVisitasView.fxml"));
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
    void onBuscarAlumnoAction(ActionEvent event) {

    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
