package es.crttn.dad.controllers.secondary.alumno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearAlumnoController implements Initializable {

    @FXML
    private TextField StudendName;

    @FXML
    private DatePicker StudendsDate;

    @FXML
    private TextField StudentSurname;

    @FXML
    private TextField StudentsClass;

    @FXML
    private TextField StudentsDNI;

    @FXML
    private TextField StudentsEmail;

    @FXML
    private TextField StudentsNUSS;

    @FXML
    private TextField StudentsPhone;

    @FXML
    private BorderPane root;

    public CrearAlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/alumno/CrearAlumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddAction(ActionEvent event) {

    }

    @FXML
    void onCancelAction(ActionEvent event) {

    }
}
