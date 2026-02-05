package atlas;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm Atlas.");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("--------------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        showLine();
        System.out.println("Oops! " + message);
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

