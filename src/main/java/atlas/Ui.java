package atlas;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for displaying messages and reading user input.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Atlas.");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return the full command string entered by the user
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("--------------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        showLine();
        System.out.println("Oops! " + message);
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

