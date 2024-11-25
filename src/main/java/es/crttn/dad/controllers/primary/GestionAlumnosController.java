package es.crttn.dad.controllers.primary;

import es.crttn.dad.controllers.secondary.CrearAlumnoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GestionAlumnosController implements Initializable {

    @FXML
    private BorderPane root;

    public GestionAlumnosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/GestionAlumnosView.fxml"));
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

        CrearAlumnoController crearAlumnoController = new CrearAlumnoController();

        Stage stage = new Stage();
        stage.setTitle("Gestión de empresas");
        stage.setScene(new Scene(crearAlumnoController.getRoot()));
        stage.show();

    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Alumno");
        dialog.setHeaderText("¿Qué alumno deseas eliminar?");
        dialog.setContentText("Introduce su DNI:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(dni -> {

            System.out.println("DNI ingresado: " + dni);
        });
    }


    @FXML
    void onFindButtonAction(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Alumno");
        dialog.setHeaderText("¿Qué alumno deseas actualizar?");
        dialog.setContentText("Introduce su DNI:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(dni -> {

            System.out.println("DNI ingresado: " + dni);
        });

    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Alumno");
        dialog.setHeaderText("¿Qué alumno deseas actualizar?");
        dialog.setContentText("Introduce su DNI:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(dni -> {

            System.out.println("DNI ingresado: " + dni);
        });
    }
}
