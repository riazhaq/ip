package atlas;


public class Atlas {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/atlas.txt");
        TaskList tasks;

        try {
            tasks = new TaskList(storage.load());
        } catch (AtlasException e) {
            ui.showMessage("Starting with an empty task list.");
            tasks = new TaskList();
        }

        ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                ParsedCommand command = Parser.parse(ui.readCommand());

                switch (command.type) {

                    case BYE:
                        ui.showGoodbye();
                        isExit = true;
                        break;

                    case LIST:
                        if (tasks.isEmpty()) {
                            ui.showMessage("Your task list is empty.");
                            break;
                        }
                        ui.showMessage("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            ui.showMessage((i + 1) + ". " + tasks.get(i));
                        }
                        break;

                    case TODO:
                        tasks.add(new Todo(command.description));
                        storage.save(tasks.getAll());
                        ui.showMessage("Got it. I've added this task:");
                        ui.showMessage("  " + tasks.get(tasks.size() - 1));
                        break;

                    case DEADLINE:
                        tasks.add(new Deadline(command.description, command.date1));
                        storage.save(tasks.getAll());
                        ui.showMessage("Got it. I've added this task:");
                        ui.showMessage("  " + tasks.get(tasks.size() - 1));
                        break;

                    case EVENT:
                        tasks.add(new Event(command.description, command.date1, command.date2));
                        storage.save(tasks.getAll());
                        ui.showMessage("Got it. I've added this task:");
                        ui.showMessage("  " + tasks.get(tasks.size() - 1));
                        break;

                    case MARK:
                        tasks.get(command.index).markDone();
                        storage.save(tasks.getAll());
                        ui.showMessage("Nice! I've marked this task as done:");
                        ui.showMessage("  " + tasks.get(command.index));
                        break;

                    case UNMARK:
                        tasks.get(command.index).markUndone();
                        storage.save(tasks.getAll());
                        ui.showMessage("OK, I've marked this task as not done yet:");
                        ui.showMessage("  " + tasks.get(command.index));
                        break;

                    case DELETE:
                        ui.showMessage("Noted. I've removed this task:");
                        ui.showMessage("  " + tasks.remove(command.index));
                        storage.save(tasks.getAll());
                        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                        break;
                }

            } catch (AtlasException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
