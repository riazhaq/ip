package atlas;

import java.util.ArrayList;

/**
 * Manages an in-memory list of tasks.
 * Provides operations to add, delete, mark, find, and sort tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /** Initializes an empty TaskList. */
    public TaskList() { this.tasks = new ArrayList<>(); }
    public void setTasks(ArrayList<Task> tasks) { this.tasks = tasks; }
    public ArrayList<Task> getTasks() { return tasks; }
    public void add(Task task) { tasks.add(task); }
    public int size() { return tasks.size(); }

    /** Sorts the tasks in the list alphabetically by their description. */
    public void sortTasks() {
        tasks.sort((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()));
    }

    /**
     * Checks if a task already exists in the list.
     * @param newTask The task to check for duplicates.
     * @return {@code true} if a duplicate exists, {@code false} otherwise.
     */
    public boolean isDuplicate(Task newTask) {
        for (Task task : tasks) {
            if (task.equals(newTask)) return true;
        }
        return false;
    }

    /**
     * Marks a task as complete based on its list index.
     * @param index 1-based index of the task.
     * @return The updated task.
     * @throws AtlasException If the index is out of bounds.
     */
    public Task markTask(int index) throws AtlasException {
        checkIndex(index);
        Task task = tasks.get(index - 1);
        task.setDone(true);
        return task;
    }

    public Task unmarkTask(int index) throws AtlasException {
        checkIndex(index);
        Task task = tasks.get(index - 1);
        task.setDone(false);
        return task;
    }

    public Task deleteTask(int index) throws AtlasException {
        checkIndex(index);
        return tasks.remove(index - 1);
    }

    /**
     * Searches for tasks whose descriptions contain the specified keyword.
     * @param keyword The search term.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    /** Helper method to validate if a list index is within valid bounds. */
    private void checkIndex(int index) throws AtlasException {
        if (index < 1 || index > tasks.size()) throw new AtlasException("Invalid task number.");
    }
}