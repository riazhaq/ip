package atlas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

/**
 * A GUI for Atlas using FXML.
 * This class handles the loading of the primary stage, setting up the scene,
 * and initializing the root layout of the application.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            String baseDir = System.getProperty("atlas.base.dir", System.getProperty("user.dir"));
            String storagePath = baseDir + File.separator + "data" + File.separator + "tasks.txt";

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Atlas Task Navigator");

            Atlas atlas = new Atlas(storagePath);
            fxmlLoader.<MainWindow>getController().setAtlas(atlas);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}