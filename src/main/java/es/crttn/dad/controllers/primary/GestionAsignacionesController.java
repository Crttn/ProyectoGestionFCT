package es.crttn.dad.controllers.primary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.secondary.asignacion.BuscarAsignacionController;
import es.crttn.dad.controllers.secondary.asignacion.CrearAsignacionController;
import es.crttn.dad.controllers.secondary.asignacion.EliminarAsignacionController;
import es.crttn.dad.controllers.secondary.asignacion.ModificarAsignacionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionAsignacionesController implements Initializable {

    CrearAsignacionController cac = new CrearAsignacionController();
    BuscarAsignacionController bac = new BuscarAsignacionController();
    ModificarAsignacionController mac = new ModificarAsignacionController();
    EliminarAsignacionController eac = new EliminarAsignacionController();

    @FXML
    private BorderPane root;

    public GestionAsignacionesController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionAsignacionPracticas.fxml"));
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
    void onFindButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(bac.getRoot());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(mac.getRoot());
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(eac.getRoot());
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getRoot());
    }
}
