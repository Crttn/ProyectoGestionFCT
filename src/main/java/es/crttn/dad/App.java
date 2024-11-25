package es.crttn.dad;

import es.crttn.dad.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Proyecto Gestión FCT");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }
}
