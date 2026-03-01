package atlas;

import java.io.IOException;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An immutable custom control using FXML that represents a message bubble in the GUI.
 * It displays a speaker's profile picture and their corresponding text message.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor to create a dialog box.
     * Loads the FXML layout for the dialog box and sets the text and image.
     *
     * @param text The message to be displayed.
     * @param img The profile image of the speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            // Using MainWindow.class ensures the /view/ path is resolved from the same root
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * Used primarily to distinguish between User and Atlas messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Factory method to create a dialog box representing the user's input.
     *
     * @param text The user's input string.
     * @param img The user's profile image.
     * @return A DialogBox instance with the image on the right.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Factory method to create a dialog box representing Atlas's response.
     * This box is flipped so the image appears on the left.
     *
     * @param text Atlas's response string.
     * @param img Atlas's profile image.
     * @return A DialogBox instance with the image on the left.
     */
    public static DialogBox getAtlasDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}