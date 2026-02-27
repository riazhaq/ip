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
        try { tasks.setTasks(storage.load()); } catch (IOException e) { }
    }

    public String getResponse(String input) {
        try {
            ParsedCommand command = Parser.parse(input);
            CommandType type = command.getCommandType();
            Task taskToAdd = null;

            switch (type) {
                case LIST: return ui.getTaskListString(tasks.getTasks());
                case FIND: return ui.getFoundTasksString(tasks.findTasks(command.getArgument()));
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
                storage.save(tasks.getTasks());
                return ui.getAddTaskString(taskToAdd, tasks.size());
            }
            return "";
        } catch (AtlasException e) { return ui.getErrorString(e.getMessage());
        } catch (IOException e) { return "Error saving data."; }
    }

    private String handleMark(int index) throws AtlasException, IOException {
        Task t = tasks.markTask(index);
        storage.save(tasks.getTasks());
        return ui.getMarkString(t);
    }

    private String handleUnmark(int index) throws AtlasException, IOException {
        Task t = tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
        return ui.getUnmarkString(t);
    }

    private String handleDelete(int index) throws AtlasException, IOException {
        Task t = tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        return ui.getDeleteString(t, tasks.size());
    }
}