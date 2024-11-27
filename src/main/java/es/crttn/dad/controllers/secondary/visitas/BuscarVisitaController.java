package es.crttn.dad.controllers.secondary.visitas;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.VisitaSeguimiento;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class BuscarVisitaController implements Initializable {

    @FXML
    private TableView<VisitaSeguimiento> visitasTableView;

    @FXML
    private TableColumn<VisitaSeguimiento, Date> fechaColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, Integer> idPracticaColumn;

    @FXML
    private TableColumn<VisitaSeguimiento, Integer> idVisitaColumn;

    @FXML
    private TextField idVisitaTextField;

    @FXML
    private TableColumn<VisitaSeguimiento, String> observacionesColumn;

    @FXML
    private BorderPane root;

    private final IntegerProperty idVisitaProperty = new SimpleIntegerProperty();
    private ObservableList listaVisitas;


    public BuscarVisitaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/visitasSeguimiento/BuscarVisitasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idVisitaTextField.textProperty().bindBidirectional(idVisitaProperty, new NumberStringConverter());

        idVisitaColumn.setCellValueFactory(cellData -> cellData.getValue().idVisitaProperty().asObject());
        idPracticaColumn.setCellValueFactory(cellData -> cellData.getValue().idPracticaProperty().asObject());
        fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        observacionesColumn.setCellValueFactory(cellData -> cellData.getValue().observacionesProperty());

        listaVisitas = FXCollections.observableArrayList();
        visitasTableView.setItems(listaVisitas);
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        listaVisitas.clear();

        String querry = "SELECT * FROM visitaseguimiento WHERE id_visita = ?";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, idVisitaProperty.getValue().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    VisitaSeguimiento visitaSeguimiento = new VisitaSeguimiento(resultSet.getInt("id_visita"), resultSet.getInt("id_practica"), resultSet.getDate("fecha"), resultSet.getString("observaciones"));
                    listaVisitas.add(visitaSeguimiento);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onSearchAllAction(ActionEvent event) {
        listaVisitas.clear();

        String querry = "SELECT * FROM visitaseguimiento";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    VisitaSeguimiento visitaSeguimiento = new VisitaSeguimiento(resultSet.getInt("id_visita"), resultSet.getInt("id_practica"), resultSet.getDate("fecha"), resultSet.getString("observaciones"));
                    listaVisitas.add(visitaSeguimiento);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGvc().getRoot());
    }
}
