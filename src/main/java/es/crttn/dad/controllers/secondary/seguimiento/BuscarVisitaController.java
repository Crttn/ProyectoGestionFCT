package es.crttn.dad.controllers.secondary.seguimiento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onBackAction(ActionEvent event) {

    }

    @FXML
    void onBuscarAlumnoAction(ActionEvent event) {

    }
}
