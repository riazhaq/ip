package atlas;

import java.util.ArrayList;

/**
 * Handles generating all user-facing messages for the Atlas Navigator.
 */
public class Ui {
    private static final String BOT_NAME = "Atlas";

    public String getWelcomeString() {
        return "Hello. I am " + BOT_NAME + ", your task navigator.\n\n"
                + "Available commands:\n\n"

                + "Add tasks:\n"
                + "  todo <description>\n"
                + "  deadline <description> /by <yyyy-mm-dd>\n"
                + "  event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n\n"

                + "Manage tasks:\n"
                + "  list\n"
                + "  find <keyword>\n"
                + "  mark <task number>\n"
                + "  unmark <task number>\n"
                + "  delete <task number>\n\n"

                + "What would you like to do?";
    }

    public String getTaskListString(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is currently empty, explorer.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String getFoundTasksString(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found in our records.";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String getAddTaskString(Task task, int size) {
        return "Understood. I've added this task to the logs:\n  " + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    public String getMarkString(Task task) {
        return "Excellent! I've marked this task as complete:\n  " + task;
    }

    public String getUnmarkString(Task task) {
        return "Acknowledged. I've marked this task as not done yet:\n  " + task;
    }

    public String getDeleteString(Task task, int size) {
        return "Task removed from the navigator records:\n  " + task
                + "\nNow you have " + size + " tasks remaining.";
    }

    public String getExitString() {
        return "Safe travels! Closing navigator systems...";
    }

    public String getErrorString(String message) {
        return "Navigator Error: " + message;
    }
}