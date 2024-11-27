package es.crttn.dad.controllers.secondary.comentarioscaptacion;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModificarComentarioController implements Initializable {


    @FXML
    private TextArea comentarioTextArea;

    @FXML
    private DatePicker fechaDatePicker;

    @FXML
    private TextField idComentarioTextfield;

    @FXML
    private TextField idEmpresaTextField;

    @FXML
    private BorderPane root;

    private IntegerProperty idComentarioProperty = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> fechaProperty = new SimpleObjectProperty<>();
    private StringProperty comentarioProperty = new SimpleStringProperty();
    private IntegerProperty empresaProperty = new SimpleIntegerProperty();

    public ModificarComentarioController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/comentarioscaptacion/ModificarComentario.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idComentarioTextfield.textProperty().bindBidirectional(idComentarioProperty, new NumberStringConverter());
        fechaDatePicker.valueProperty().bindBidirectional(fechaProperty);
        comentarioTextArea.textProperty().bindBidirectional(comentarioProperty);
        idEmpresaTextField.textProperty().bindBidirectional(empresaProperty, new NumberStringConverter());
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        String querry = "SELECT * FROM comnetariocaptacion WHERE id_comentario = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            statement.setString(1, idComentarioProperty.getValue().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    fechaProperty.setValue(resultSet.getDate("fecha").toLocalDate());
                    comentarioProperty.setValue(resultSet.getString("comentario"));
                    empresaProperty.setValue(resultSet.getInt("id_empresa"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onApplyAction(ActionEvent event) {
        String querry = "UPDATE comnetariocaptacion SET fecha = ?, comentario = ?, id_empresa = ?";

        if (fechaProperty.getValue() != null && comentarioProperty.getValue() != null && empresaProperty.getValue() != null) {
            try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setDate(1, Date.valueOf(fechaProperty.getValue()));
                statement.setString(2, comentarioProperty.getValue());
                statement.setInt(3, empresaProperty.getValue());

                //Ejecuta la consulta
                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modificación Realizada");
                alert.setHeaderText("Se ha realizado correctamente la modificación de los datos.");
                alert.showAndWait();

                // Limpiar campos
                fechaProperty.setValue(null);
                comentarioProperty.setValue(null);
                empresaProperty.setValue(null);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGcc().getRoot());
    }

}
