package atlas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Atlas using FXML.
 * This class handles the loading of the primary stage, setting up the scene,
 * and initializing the root layout of the application.
 */
public class Main extends Application {

    private Atlas atlas = new Atlas();

    /**
     * Starts the JavaFX application by loading the FXML for the main window,
     * setting the scene, and injecting the Atlas logic engine into the controller.
     *
     * @param stage The primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Atlas Task Navigator");

            // Inject the Atlas instance into the controller
            fxmlLoader.<MainWindow>getController().setAtlas(new Atlas());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}