package atlas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Atlas atlas = new Atlas();

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