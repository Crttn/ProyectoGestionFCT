package es.crttn.dad.controllers.secondary.practicas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscarAsignacionController implements Initializable {

    @FXML
    private TableView<?> asignacionView;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> columnafechafin;

    @FXML
    private TableColumn<?, ?> columnafechainicio;

    @FXML
    private TableColumn<?, ?> columnaidalumno;

    @FXML
    private TableColumn<?, ?> columnaidempresa;

    @FXML
    private TableColumn<?, ?> columnaidpractica;

    @FXML
    private TableColumn<?, ?> columnaidtutordocente;

    @FXML
    private Button findButton;

    @FXML
    private TextField idcomentariotextfield;

    @FXML
    private TableColumn<?, ?> idtutorempresa;

    @FXML
    private BorderPane root;

    public BuscarAsignacionController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/asignacionpracticas/BuscarAsignacion.fxml"));
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
    void onBuscarAlumnoAction(ActionEvent event) {

    }
}
