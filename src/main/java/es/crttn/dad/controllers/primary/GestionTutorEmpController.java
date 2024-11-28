package es.crttn.dad.controllers.primary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.secondary.tutores.empresa.BuscarTutorEmpresaController;
import es.crttn.dad.controllers.secondary.tutores.empresa.CrearTutorEmpresaController;
import es.crttn.dad.controllers.secondary.tutores.empresa.EliminarTutorEmpresaController;
import es.crttn.dad.controllers.secondary.tutores.empresa.ModificarTutorEmpresaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionTutorEmpController implements Initializable {

    CrearTutorEmpresaController ctec = new CrearTutorEmpresaController();
    BuscarTutorEmpresaController btec = new BuscarTutorEmpresaController();
    ModificarTutorEmpresaController mtec = new ModificarTutorEmpresaController();
    EliminarTutorEmpresaController etec = new EliminarTutorEmpresaController();

    @FXML
    private BorderPane root;

    public GestionTutorEmpController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionTutorEmprView.fxml"));
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
        App.getRootController().getRoot().setCenter(ctec.getRoot());
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(etec.getRoot());
    }

    @FXML
    void onFindButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(btec.getRoot());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(mtec.getRoot());
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGtc().getRoot());
    }

    public CrearTutorEmpresaController getCtec() {
        return ctec;
    }

    public BuscarTutorEmpresaController getBtec() {
        return btec;
    }

    public ModificarTutorEmpresaController getMtec() {
        return mtec;
    }

    public EliminarTutorEmpresaController getEtec() {
        return etec;
    }
}
