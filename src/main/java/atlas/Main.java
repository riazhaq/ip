package atlas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Atlas atlas = new Atlas();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml")
            );
            AnchorPane root = loader.load();

            MainWindow controller = loader.getController();
            controller.setAtlas(atlas);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Atlas");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}