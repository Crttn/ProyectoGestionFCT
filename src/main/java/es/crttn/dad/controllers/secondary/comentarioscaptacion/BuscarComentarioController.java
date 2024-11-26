package es.crttn.dad.controllers.secondary.comentarioscaptacion;

import es.crttn.dad.App;
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

public class BuscarComentarioController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> columnaidempresa;

    @FXML
    private TableView<?> comentarioCaptacionView;

    @FXML
    private TableColumn<?, ?> comentarios;

    @FXML
    private TableColumn<?, ?> culumnaidcomentario;

    @FXML
    private TableColumn<?, ?> fecha;

    @FXML
    private Button findButton;

    @FXML
    private TextField idcomentariotextfield;

    @FXML
    private BorderPane root;

    public BuscarComentarioController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/comentarioscaptacion/BuscarComentario.fxml"));
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
    void onFindButtonAction(ActionEvent event) {

    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGcc().getRoot());
    }
}
