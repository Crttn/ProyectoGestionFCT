package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearVisitaController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea observacionesArea;

    @FXML
    private BorderPane root;

    @FXML
    private DatePicker visitaFecha;

    public CrearVisitaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/visitasSeguimiento/CrearVisitas.fxml"));
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
    void onAddButtonAction(ActionEvent event) {

    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
