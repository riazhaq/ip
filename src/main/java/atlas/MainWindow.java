package atlas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Atlas atlas;

    // Load images using Stream for better reliability across different systems
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image atlasImage = new Image(this.getClass().getResourceAsStream("/images/DaAtlas.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAtlas(Atlas a) {
        atlas = a;

        String welcomeMessage = atlas.getWelcomeString();
        dialogContainer.getChildren().addAll(
                DialogBox.getAtlasDialog(welcomeMessage, atlasImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Atlas's reply.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return; // Do nothing for empty input to avoid errors
        }
        String response = atlas.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAtlasDialog(response, atlasImage)
        );
        userInput.clear();
    }
}