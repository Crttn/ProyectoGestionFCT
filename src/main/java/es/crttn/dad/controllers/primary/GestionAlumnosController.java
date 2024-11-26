package es.crttn.dad.controllers.primary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.secondary.alumno.BuscarAlumnoController;
import es.crttn.dad.controllers.secondary.alumno.CrearAlumnoController;
import es.crttn.dad.controllers.secondary.alumno.EliminarAlumnoController;
import es.crttn.dad.controllers.secondary.alumno.ModificarAlumnoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GestionAlumnosController implements Initializable {

    BuscarAlumnoController bac = new BuscarAlumnoController();
    CrearAlumnoController cac = new CrearAlumnoController();
    EliminarAlumnoController eac = new EliminarAlumnoController();
    ModificarAlumnoController mac = new ModificarAlumnoController();

    @FXML
    private BorderPane root;

    public GestionAlumnosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionAlumnosView.fxml"));
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
        App.getRootController().getRoot().setCenter(cac.getRoot());
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(eac.getRoot());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(mac.getRoot());
    }

    @FXML
    void onFindButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(bac.getRoot());
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getRoot());
    }
}
