package atlas;

import javafx.application.Application;
import java.io.File;
import java.net.URISyntaxException;

/**
 * A simple launcher class to serve as the entry point for the application.
 * This class is used to work around classpath issues that occur when
 * launching JavaFX applications directly from a JAR file.
 */
public class Launcher {
    public static void main(String[] args) {
        String jarPath = "";
        try {
            jarPath = new File(Launcher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            jarPath = System.getProperty("user.dir");
        }

        System.setProperty("atlas.base.dir", jarPath);
        Application.launch(Main.class, args);
    }
}
