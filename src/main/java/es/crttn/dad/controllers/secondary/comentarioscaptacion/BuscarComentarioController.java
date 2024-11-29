package es.crttn.dad.controllers.secondary.comentarioscaptacion;

import es.crttn.dad.App;
import es.crttn.dad.DatabaseManager;
import es.crttn.dad.models.ComentarioCaptacion;
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

public class BuscarComentarioController implements Initializable {

    @FXML
    private TableColumn<ComentarioCaptacion, Integer> idDocenteColumn;

    @FXML
    private TableColumn<ComentarioCaptacion, String> nombreDocenteColumn;

    @FXML
    private TableColumn<ComentarioCaptacion, String> nombreEmpresaColumn;

    @FXML
    private TableView<ComentarioCaptacion> comentarioTableView;

    @FXML
    private TableColumn<ComentarioCaptacion, String> comentarioColumn;

    @FXML
    private TableColumn<ComentarioCaptacion, Date> fechaColumn;

    @FXML
    private TableColumn<ComentarioCaptacion, Integer> idComentarioColumn;

    @FXML
    private TableColumn<ComentarioCaptacion, Integer> idEmpresaColumn;

    @FXML
    private TextField idcomentariotextfield;

    @FXML
    private BorderPane root;

    private final IntegerProperty idComentraioProperty = new SimpleIntegerProperty();
    private ObservableList listaComentarios;

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

        idcomentariotextfield.textProperty().bindBidirectional(idComentraioProperty, new NumberStringConverter());
        idcomentariotextfield.setText("");
        idcomentariotextfield.setFocusTraversable(false);

        idComentarioColumn.setCellValueFactory(cellData -> cellData.getValue().idComentarioProperty().asObject());
        idEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().idEmpresaProperty().asObject());
        fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        comentarioColumn.setCellValueFactory(cellData -> cellData.getValue().comentarioProperty());
        nombreEmpresaColumn.setCellValueFactory(cellData -> cellData.getValue().nombreEmpresaProperty());
        idDocenteColumn.setCellValueFactory(cellData -> cellData.getValue().idDocenteProperty().asObject());
        nombreDocenteColumn.setCellValueFactory(cellData -> cellData.getValue().nombrrDocenteProperty());


        listaComentarios = FXCollections.observableArrayList();
        comentarioTableView.setItems(listaComentarios);
    }

    public BorderPane getRoot() {
        return root;
    }


    @FXML
    void onSearchAction(ActionEvent event) {
        listaComentarios.clear();

        String querry = "SELECT comnetariocaptacion.*, empresa.nombre,tutordocente.id_tutor_docente, tutordocente.nombre FROM `comnetariocaptacion` INNER JOIN empresa on comnetariocaptacion.id_empresa = empresa.id_empresa INNER JOIN tutordocente on comnetariocaptacion.id_tutor_docente = tutordocente.id_tutor_docente WHERE id_empresa = ?;";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            // Usar los valores obtenidos de las propiedades
            statement.setString(1, idComentraioProperty.getValue().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ComentarioCaptacion comentarioCaptacion = new ComentarioCaptacion(
                            resultSet.getInt("id_comentario"),
                            resultSet.getDate("fecha"),
                            resultSet.getString("comentario"),
                            resultSet.getInt("id_empresa"),
                            resultSet.getString("empresa.nombre"),
                            resultSet.getInt("id_tutor_docente"),
                            resultSet.getString("tutordocente.nombre"));
                    listaComentarios.add(comentarioCaptacion);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearchAllAction(ActionEvent event) {
        listaComentarios.clear();

        String querry = "SELECT comnetariocaptacion.*, empresa.nombre,tutordocente.id_tutor_docente, tutordocente.nombre FROM `comnetariocaptacion` INNER JOIN empresa on comnetariocaptacion.id_empresa = empresa.id_empresa INNER JOIN tutordocente on comnetariocaptacion.id_tutor_docente = tutordocente.id_tutor_docente;";

        try (Connection connection = DatabaseManager.getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement(querry)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ComentarioCaptacion comentarioCaptacion = new ComentarioCaptacion(
                            resultSet.getInt("id_comentario"),
                            resultSet.getDate("fecha"),
                            resultSet.getString("comentario"),
                            resultSet.getInt("id_empresa"),
                            resultSet.getString("empresa.nombre"),
                            resultSet.getInt("id_tutor_docente"),
                            resultSet.getString("tutordocente.nombre"));
                    listaComentarios.add(comentarioCaptacion);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackAction(ActionEvent event) {
        App.getRootController().getRoot().setCenter(App.getRootController().getGestionMainController().getGcc().getRoot());
    }
}
