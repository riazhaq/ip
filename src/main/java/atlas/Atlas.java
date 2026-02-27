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
            tasks.setTasks(storage.load());
        } catch (IOException e) {
            // If file not found, start with empty list
        }
    }

    public String getResponse(String input) {
        try {
            ParsedCommand command = Parser.parse(input);
            CommandType type = command.getCommandType();

            switch (type) {

                case LIST:
                    return ui.getTaskListString(tasks.getTasks());

                case FIND:
                    return ui.getFoundTasksString(
                            tasks.findTasks(command.getArgument())
                    );

                case TODO:
                    Task todo = new Todo(command.getArgument());
                    tasks.add(todo);
                    storage.save(tasks.getTasks());
                    return ui.getAddTaskString(todo, tasks.size());

                case DEADLINE:
                    Task deadline = new Deadline(
                            command.getArgument(),
                            command.getDate()
                    );
                    tasks.add(deadline);
                    storage.save(tasks.getTasks());
                    return ui.getAddTaskString(deadline, tasks.size());

                case EVENT:
                    Task event = new Event(
                            command.getArgument(),
                            command.getFrom(),
                            command.getTo()
                    );
                    tasks.add(event);
                    storage.save(tasks.getTasks());
                    return ui.getAddTaskString(event, tasks.size());

                case MARK:
                    Task marked = tasks.markTask(command.getIndex());
                    storage.save(tasks.getTasks());
                    return ui.getMarkString(marked);

                case UNMARK:
                    Task unmarked = tasks.unmarkTask(command.getIndex());
                    storage.save(tasks.getTasks());
                    return ui.getUnmarkString(unmarked);

                case DELETE:
                    Task deleted = tasks.deleteTask(command.getIndex());
                    storage.save(tasks.getTasks());
                    return ui.getDeleteString(deleted, tasks.size());

                case EXIT:
                    return ui.getExitString();

                default:
                    return ui.getErrorString("Unknown command.");
            }

        } catch (AtlasException e) {
            return ui.getErrorString(e.getMessage());
        } catch (IOException e) {
            return "Error saving data.";
        }
    }
}