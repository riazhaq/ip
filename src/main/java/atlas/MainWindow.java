package atlas;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.util.Objects;

public class MainWindow {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private Atlas atlas;

    private Image userImage;
    private Image atlasImage;

    @FXML
    public void initialize() {
        // Auto-scroll to bottom
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Load images SAFELY inside initialize (not at field level)
        userImage = new Image(
                Objects.requireNonNull(
                        getClass().getResource("/images/DaUser.png")
                ).toExternalForm()
        );

        atlasImage = new Image(
                Objects.requireNonNull(
                        getClass().getResource("/images/DaAtlas.png")
                ).toExternalForm()
        );
    }

    public void setAtlas(Atlas atlas) {
        this.atlas = atlas;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = atlas.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAtlasDialog(response, atlasImage)
        );

        userInput.clear();
    }
}