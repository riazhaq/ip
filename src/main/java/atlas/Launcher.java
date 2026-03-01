package atlas;

import javafx.application.Application;

/**
 * A simple launcher class to serve as the entry point for the application.
 * This class is used to work around classpath issues that occur when
 * launching JavaFX applications directly from a JAR file.
 */
public class Launcher {
    /**
     * The main entry point for the entire application.
     * * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
