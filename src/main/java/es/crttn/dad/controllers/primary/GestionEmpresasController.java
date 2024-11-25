package es.crttn.dad.controllers.primary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.RootController;
import es.crttn.dad.controllers.secondary.BuscarEmpresaController;
import es.crttn.dad.controllers.secondary.CrearEmpresaController;
import es.crttn.dad.controllers.secondary.EliminarEmpresaController;
import es.crttn.dad.controllers.secondary.ModificaEmpresaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionEmpresasController implements Initializable {

    private CrearEmpresaController cec = new CrearEmpresaController();
    private EliminarEmpresaController eec = new EliminarEmpresaController();
    private ModificaEmpresaController mec = new ModificaEmpresaController();
    private BuscarEmpresaController bec = new BuscarEmpresaController();


    @FXML
    private BorderPane root;

    public GestionEmpresasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionEmpresasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(cec.getRoot());
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
       App.getRootController().getRoot().setCenter(eec.getRoot());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(mec.getRoot());
    }

    @FXML
    void onFindButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(bec.getRoot());
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getRoot());
    }
}
