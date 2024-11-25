package es.crttn.dad.controllers.secondary.empresa;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ModificarEmpresaController implements Initializable {

    private ModificarEmpresa1Controller me1c = new ModificarEmpresa1Controller();

    private final StringProperty nombreProperty = new SimpleStringProperty();

    @FXML
    private TextField nombreEmpresaTextField;

    @FXML
    private BorderPane root;

    public ModificarEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/empresa/ModificarEmpresaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreEmpresaTextField.textProperty().bindBidirectional(nombreProperty);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        String querry = "SELECT * FROM alumno where dni = ?";

        if (nombreProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, nombreProperty.getValue());

                App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onBackaction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());
    }

    public String nombreEmpresa() {
        return nombreProperty.getValue();
    }

    public ModificarEmpresa1Controller getMe1c() {
        return me1c;
    }
}
