package es.crttn.dad.controllers.secondary.empresa;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ModificarEmpresaController implements Initializable {

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField direccionTextField;

    @FXML
    private TextField especialidadTextFiedl;

    @FXML
    private TextField horarioTextField;

    @FXML
    private TextField nombreEmpresaTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField plazasTextField;

    @FXML
    private BorderPane root;

    private final StringProperty nombreProperty = new SimpleStringProperty();
    private final StringProperty direccionProperty = new SimpleStringProperty();
    private final StringProperty correoProperty = new SimpleStringProperty();
    private final StringProperty horarioProperty = new SimpleStringProperty();
    private final IntegerProperty plazasProperty = new SimpleIntegerProperty();
    private final StringProperty especialidadProperty = new SimpleStringProperty();


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
        direccionTextField.textProperty().bindBidirectional(direccionProperty);
        correoTextField.textProperty().bindBidirectional(correoProperty);
        horarioTextField.textProperty().bindBidirectional(horarioProperty);
        plazasTextField.textProperty().bindBidirectional(plazasProperty, new NumberStringConverter());
        especialidadTextFiedl.textProperty().bindBidirectional(especialidadProperty);


    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        String querry = "select nombre,direccion, correo, horario, plazas_disp, especialidad from empresa WHERE nombre = ?";


        if (nombreProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setString(1, nombreProperty.getValue());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        nombreProperty.setValue(resultSet.getString("nombre"));
                        direccionProperty.setValue(resultSet.getString("direccion"));
                        correoProperty.setValue(resultSet.getString("horario"));
                        horarioProperty.setValue(resultSet.getString("horario"));
                        plazasProperty.setValue(resultSet.getInt("plazas_disp"));
                        especialidadProperty.setValue(resultSet.getString("especialidad"));


                    }
                }

                nombreTextField.setText(nombreProperty.getValue());
                direccionTextField.setText(direccionProperty.getValue());
                correoTextField.setText(correoProperty.getValue());
                horarioTextField.setText(horarioProperty.getValue());
                plazasTextField.setText(plazasProperty.getValue().toString());
                especialidadTextFiedl.setText(especialidadProperty.getValue());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onModifyAction(ActionEvent event) {
        String querry = "UPDATE empresa SET nombre = ?, direccion = ?, correo = ?, horario = ? plazas_disp = ?, especialidad = ? WHERE nombre = ?";

    }

    @FXML
    void onBackaction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGec().getRoot());

    }
}
