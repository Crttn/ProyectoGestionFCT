package es.crttn.dad.controllers.primary;

import es.crttn.dad.App;
import es.crttn.dad.controllers.secondary.comentarioscaptacion.BuscarComentarioController;
import es.crttn.dad.controllers.secondary.comentarioscaptacion.CrearComentarioController;
import es.crttn.dad.controllers.secondary.comentarioscaptacion.EliminarComentarioController;
import es.crttn.dad.controllers.secondary.comentarioscaptacion.ModificarComentarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionComentariosController implements Initializable {

    private BuscarComentarioController bcc = new BuscarComentarioController();
    private CrearComentarioController ccc = new CrearComentarioController();
    private EliminarComentarioController ecc = new EliminarComentarioController();
    private ModificarComentarioController mcc = new ModificarComentarioController();

    @FXML
    private BorderPane root;

    public GestionComentariosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionComentariosCaptacion.fxml"));
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
        App.getRootController().getRoot().setCenter(ccc.getRoot());
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(ecc.getRoot());
    }

    @FXML
    void onFindButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(bcc.getRoot());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(mcc.getRoot());
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getRoot());
    }
}
