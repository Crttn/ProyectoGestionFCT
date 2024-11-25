package es.crttn.dad.controllers.secondary.empresa;

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

public class CrearEmpresaController implements Initializable {

    @FXML
    private TextField correoEmpresa;

    @FXML
    private TextField direccionEmpresa;

    @FXML
    private TextField horarioEmpresa;

    @FXML
    private TextField nombreEmpresa;

    @FXML
    private TextField nombreTutorEmpresa;

    @FXML
    private TextField plazasDisponibles;

    @FXML
    private TextField especialidadEmpresa;

    @FXML
    private BorderPane root;

    StringProperty nombreProperty = new SimpleStringProperty();
    StringProperty direccionProperty = new SimpleStringProperty();
    StringProperty correoProperty = new SimpleStringProperty();
    StringProperty horarioProperty = new SimpleStringProperty();
    StringProperty plazasProperty = new SimpleStringProperty();
    StringProperty nombreTutorProperty = new SimpleStringProperty();
    StringProperty especialidadProperty = new SimpleStringProperty();

    public CrearEmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/empresa/CrearEmpresaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nombreEmpresa.textProperty().bindBidirectional(nombreProperty);
        direccionEmpresa.textProperty().bindBidirectional(direccionProperty);
        correoEmpresa.textProperty().bindBidirectional(correoProperty);
        horarioEmpresa.textProperty().bindBidirectional(horarioProperty);
        plazasDisponibles.textProperty().bindBidirectional(plazasProperty);
        nombreTutorEmpresa.textProperty().bindBidirectional(nombreTutorProperty);
        especialidadEmpresa.textProperty().bindBidirectional(especialidadProperty);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onCrearEmpresaAction(ActionEvent event) {

        String querry = "INSERT INTO empresa (nombre, direccion, correo, horario, plazas_disp, especialidad) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, nombreProperty.getValue());
            statement.setString(2, direccionProperty.getValue());
            statement.setString(3, correoProperty.getValue());
            statement.setString(4, horarioProperty.getValue());
            statement.setString(5, plazasProperty.getValue());
            statement.setString(6, especialidadProperty.getValue());

            int filasAfectadas = statement.executeUpdate();
            System.out.println("Usuario insertado: " + filasAfectadas + " fila(s) afectada(s)");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
