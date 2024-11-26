package es.crttn.dad.controllers.primary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.secondary.visitas.BuscarVisitaController;
import es.crttn.dad.controllers.secondary.visitas.CrearVisitaController;
import es.crttn.dad.controllers.secondary.visitas.EliminarVisitaController;
import es.crttn.dad.controllers.secondary.visitas.ModificarVisitasController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionVisitasController implements Initializable {

    private CrearVisitaController cvc = new CrearVisitaController();
    private BuscarVisitaController bvc = new BuscarVisitaController();
    private ModificarVisitasController mvc = new ModificarVisitasController();
    private EliminarVisitaController evc = new EliminarVisitaController();

    @FXML
    private BorderPane root;

    public GestionVisitasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionVisitasSeguimiento.fxml"));
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
        App.getRootController().getRoot().setCenter(cvc.getRoot());
    }

    @FXML
    void onFindButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(bvc.getRoot());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(mvc.getRoot());
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(evc.getRoot());
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getRoot());
    }
}
