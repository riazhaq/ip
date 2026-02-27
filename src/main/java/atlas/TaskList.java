package atlas;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() { this.tasks = new ArrayList<>(); }
    public void setTasks(ArrayList<Task> tasks) { this.tasks = tasks; }
    public ArrayList<Task> getTasks() { return tasks; }
    public void add(Task task) { tasks.add(task); }
    public int size() { return tasks.size(); }

    public void sortTasks() {
        tasks.sort((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()));
    }

    public boolean isDuplicate(Task newTask) {
        for (Task task : tasks) {
            if (task.equals(newTask)) return true;
        }
        return false;
    }

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

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    private void checkIndex(int index) throws AtlasException {
        if (index < 1 || index > tasks.size()) throw new AtlasException("Invalid task number.");
    }
}