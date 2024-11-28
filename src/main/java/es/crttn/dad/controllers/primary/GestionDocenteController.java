package es.crttn.dad.controllers.primary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.secondary.tutores.docente.BuscarTutorController;
import es.crttn.dad.controllers.secondary.tutores.docente.CrearTutorController;
import es.crttn.dad.controllers.secondary.tutores.docente.EliminarTutorController;
import es.crttn.dad.controllers.secondary.tutores.docente.ModificarTutorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionDocenteController implements Initializable {

    CrearTutorController ctc = new CrearTutorController();
    BuscarTutorController btc = new BuscarTutorController();
    ModificarTutorController mtc = new ModificarTutorController();
    EliminarTutorController etc = new EliminarTutorController();

    @FXML
    private BorderPane root;

    public GestionDocenteController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionDocenteView.fxml"));
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
        App.getRootController().getRoot().setCenter(ctc.getRoot());
    }

    @FXML
    void onFindButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(btc.getRoot());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(mtc.getRoot());
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(etc.getRoot());
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGtc().getRoot());
    }
}
