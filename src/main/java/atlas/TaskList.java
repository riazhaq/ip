package atlas;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    // ✅ ADD THIS
    public void add(Task task) {
        tasks.add(task);
    }

    // ✅ ADD THIS
    public int size() {
        return tasks.size();
    }

    // Already used by list
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Level 9: find
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerKeyword)) {
                results.add(task);
            }
        }

        return results;
    }
}
