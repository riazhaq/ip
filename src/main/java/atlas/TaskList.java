package atlas;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        // Assertion: The loaded task list should not be null
        assert tasks != null : "TaskList initialized with null list";
        this.tasks = tasks;
    }

    public void add(Task task) {
        int initialSize = tasks.size();
        tasks.add(task);
        // Assertion: Size must increase after adding
        assert tasks.size() == initialSize + 1 : "Task was not added to list";
    }

    public Task get(int index) {
        // Assertion: The index requested must be within the current list size
        assert index >= 0 && index < tasks.size() : "Requested task index is out of bounds";
        return tasks.get(index);
    }

    public Task remove(int index) {
        // Assertion: The index to remove must be valid
        assert index >= 0 && index < tasks.size() : "Delete index is out of bounds";
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
}