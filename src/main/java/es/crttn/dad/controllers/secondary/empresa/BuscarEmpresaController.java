package es.crttn.dad.controllers.secondary.empresa;

import es.crttn.dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscarEmpresaController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> buscarempresaView;

    @FXML
    private TableColumn<?, ?> columnacorreo;

    @FXML
    private TableColumn<?, ?> columnadireccion;

    @FXML
    private TableColumn<?, ?> columnaespecialidad;

    @FXML
    private TableColumn<?, ?> columnahorario;

    @FXML
    private TableColumn<?, ?> columnaidempresa;

    @FXML
    private TableColumn<?, ?> columnaidtutorempresa;

    @FXML
    private TableColumn<?, ?> columnanombre;

    @FXML
    private TableColumn<?, ?> columnaplazas;

    @FXML
    private Button findButton;

    @FXML
    private TextField idcomentariotextfield;

    @FXML
    private BorderPane root;

    public BuscarEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/empresa/BuscarEmpresaView.fxml"));
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
    void onBuscarAlumnoAction(ActionEvent event) {

    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());
    }

}
