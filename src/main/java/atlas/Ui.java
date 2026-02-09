package atlas;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for displaying messages and reading user input.
 */
public class Ui {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void showTaskList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }

        printLine();
    }

    public void showTaskAdded(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }


    public static void showFoundTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }

        printLine();
    }

    public static void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }
}

