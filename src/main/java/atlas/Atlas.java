package atlas;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class Atlas {

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Atlas() {
        ui = new Ui();
        storage = new Storage("data/atlas.txt");
        tasks = new TaskList();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = scanner.nextLine();
                ParsedCommand command = Parser.parse(input);

                switch (command.getCommandType()) {

                    case TODO: {
                        Task todo = new Todo(command.getArgument());
                        tasks.add(todo);
                        ui.showTaskAdded(todo, tasks.size());
                        break;
                    }

                    case DEADLINE: {
                        String[] parts = command.getArgument().split("/by");
                        String description = parts[0].trim();
                        LocalDate by = LocalDate.parse(parts[1].trim());

                        Task deadline = new Deadline(description, by);
                        tasks.add(deadline);
                        ui.showTaskAdded(deadline, tasks.size());
                        break;
                    }


                    case EVENT: {
                        String[] parts = command.getArgument().split("/from|/to");
                        String description = parts[0].trim();
                        LocalDate from = LocalDate.parse(parts[1].trim());
                        LocalDate to = LocalDate.parse(parts[2].trim());

                        Task event = new Event(description, from, to);
                        tasks.add(event);
                        ui.showTaskAdded(event, tasks.size());
                        break;
                    }



                    case LIST:
                        ui.showTaskList(tasks.getTasks());
                        break;

                    case CommandType.FIND:

                        ArrayList<Task> foundTasks =
                                tasks.findTasks(command.getArgument());
                        ui.showFoundTasks(foundTasks);
                        break;

                    case CommandType.EXIT:
                        isExit = true;
                        break;

                    // other cases (todo, deadline, mark, etc.)
                    default:
                        break;
                }

            } catch (AtlasException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Atlas().run();
    }
}
