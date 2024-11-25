package es.crttn.dad;

import es.crttn.dad.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.image.TileObserver;

public class App extends Application{

    private static RootController rootController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        rootController = new RootController();

        primaryStage.setTitle("Proyecto Gestión FCT");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Cerrar el pool de conexiones al cerrar la aplicación
        DatabaseManager.closePool();
        System.out.println("Pool de conexiones cerrado correctamente.");
    }

    public static RootController getRootController() {
        return rootController;
    }
}
