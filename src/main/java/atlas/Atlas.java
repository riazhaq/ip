package atlas;

import java.io.IOException;

public class Atlas {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Atlas() {
        ui = new Ui();
        storage = new Storage("data/atlas.txt");
        tasks = new TaskList();
        try {
            // Load existing tasks on startup
            tasks.setTasks(storage.load());
        } catch (IOException e) {
            // Fallback to empty list if loading fails
        }
    }

    public String getResponse(String input) {
        try {
            ParsedCommand command = Parser.parse(input);
            CommandType type = command.getCommandType();
            Task taskToAdd = null;

            switch (type) {
                case LIST: return ui.getTaskListString(tasks.getTasks());
                case FIND: return ui.getFoundTasksString(tasks.findTasks(command.getArgument()));
                case SORT:
                    tasks.sortTasks();
                    storage.save(tasks.getTasks());
                    return "List sorted alphabetically!\n" + ui.getTaskListString(tasks.getTasks());
                case MARK: return handleMark(command.getIndex());
                case UNMARK: return handleUnmark(command.getIndex());
                case DELETE: return handleDelete(command.getIndex());
                case TODO: taskToAdd = new Todo(command.getArgument()); break;
                case DEADLINE: taskToAdd = new Deadline(command.getArgument(), command.getDate()); break;
                case EVENT: taskToAdd = new Event(command.getArgument(), command.getFrom(), command.getTo()); break;
                case EXIT: return ui.getExitString();
                default: return ui.getErrorString("Unknown command.");
            }

            if (taskToAdd != null) {
                if (tasks.isDuplicate(taskToAdd)) {
                    throw new AtlasException("Duplicate detected! This task already exists.");
                }
                tasks.add(taskToAdd);
                storage.save(tasks.getTasks()); // Save after adding
                return ui.getAddTaskString(taskToAdd, tasks.size());
            }
            return "";
        } catch (AtlasException e) {
            return ui.getErrorString(e.getMessage());
        } catch (IOException e) {
            return "Error saving data.";
        }
    }

    private String handleMark(int index) throws AtlasException, IOException {
        Task t = tasks.markTask(index);
        storage.save(tasks.getTasks()); // Save after marking
        return ui.getMarkString(t);
    }

    private String handleUnmark(int index) throws AtlasException, IOException {
        Task t = tasks.unmarkTask(index);
        storage.save(tasks.getTasks()); // Save after unmarking
        return ui.getUnmarkString(t);
    }

    private String handleDelete(int index) throws AtlasException, IOException {
        Task t = tasks.deleteTask(index);
        storage.save(tasks.getTasks()); // Save after deleting
        return ui.getDeleteString(t, tasks.size());
    }

    // Inside Atlas.java
    public String getWelcomeString() {
        return ui.getWelcomeString();
    }
}