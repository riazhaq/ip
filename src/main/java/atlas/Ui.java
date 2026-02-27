package atlas;

import java.util.ArrayList;

public class Ui {

    public String getTaskListString(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        return sb.toString();
    }

    public String getFoundTasksString(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        return sb.toString();
    }

    public String getAddTaskString(Task task, int size) {
        return "Got it. I've added this task:\n  "
                + task
                + "\nNow you have "
                + size
                + " tasks in the list.";
    }

    public String getMarkString(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String getUnmarkString(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    public String getDeleteString(Task task, int size) {
        return "Noted. I've removed this task:\n  "
                + task
                + "\nNow you have "
                + size
                + " tasks in the list.";
    }

    public String getExitString() {
        return "Bye! Hope to see you again soon!";
    }

    public String getErrorString(String message) {
        return "Error: " + message;
    }
}