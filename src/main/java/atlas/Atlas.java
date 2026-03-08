package atlas;

import java.io.IOException;

/**
 * The main logic controller for the Atlas task management system.
 * It coordinates interaction between the user interface, task storage, and task list operations.
 */
public class Atlas {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Initializes a new Atlas instance.
     * Sets up the UI, storage path, and attempts to load existing tasks from the data file.
     */
    // Main constructor
    public Atlas(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            this.tasks.setTasks(storage.load());
        } catch (IOException e) {
        }
    }

    // Default constructor (chains to the main constructor)
    public Atlas() {
        this("data/atlas.txt");
    }

    /**
     * Processes user input and returns a string response.
     * This method handles the parsing of commands, execution of task operations,
     * duplicate detection, and data persistence.
     *
     * @param input The raw string input from the user.
     * @return A formatted response string to be displayed in the GUI.
     */
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

    /**
     * Handles the logic for marking a task as complete.
     * * @param index The 1-based index of the task in the list.
     * @return A confirmation message from the UI.
     * @throws AtlasException If the index is invalid.
     * @throws IOException If there is an error saving the updated list.
     */
    private String handleMark(int index) throws AtlasException, IOException {
        Task t = tasks.markTask(index);
        storage.save(tasks.getTasks()); // Save after marking
        return ui.getMarkString(t);
    }

    /**
     * Marks a task as incomplete
     * * @param index The 1-based index of the task in the list.
     * @return A confirmation message from the UI.
     * @throws AtlasException If the index is invalid.
     * @throws IOException If there is an error saving the updated list.
     */
    private String handleUnmark(int index) throws AtlasException, IOException {
        Task t = tasks.unmarkTask(index);
        storage.save(tasks.getTasks()); // Save after unmarking
        return ui.getUnmarkString(t);
    }

    /**
     * Handles the removal of a task from the list.
     * * @param index The 1-based index of the task to be deleted.
     * @return A confirmation message indicating the task was removed.
     * @throws AtlasException If the index is invalid.
     * @throws IOException If there is an error saving the updated list.
     */
    private String handleDelete(int index) throws AtlasException, IOException {
        Task t = tasks.deleteTask(index);
        storage.save(tasks.getTasks()); // Save after deleting
        return ui.getDeleteString(t, tasks.size());
    }

    /**
     * Retrieves the initial greeting and instruction message for the user.
     *
     * @return A welcome string containing the Quick Start guide.
     */
    public String getWelcomeString() {
        return ui.getWelcomeString(); //Improvement of code quality
    }
}