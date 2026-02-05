import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Atlas {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/atlas.txt");
        TaskList tasks;

        // Load tasks on startup
        try {
            tasks = new TaskList(storage.load());
        } catch (AtlasException e) {
            ui.showMessage("Starting with an empty task list.");
            tasks = new TaskList();
        }

        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();

                if (input.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }

                else if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        ui.showMessage("Your task list is empty.");
                        continue;
                    }
                    ui.showMessage("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        ui.showMessage((i + 1) + ". " + tasks.get(i));
                    }
                }

                else if (input.startsWith("todo")) {
                    if (input.length() <= 4) {
                        throw new AtlasException("A todo needs a description.");
                    }
                    String description = input.substring(5);
                    tasks.add(new Todo(description));
                    storage.save(tasks.getAll());

                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage("  " + tasks.get(tasks.size() - 1));
                }

                else if (input.startsWith("deadline")) {
                    if (!input.contains("/by")) {
                        throw new AtlasException("A deadline needs a /by date.");
                    }

                    String[] parts = input.substring(9).split("/by", 2);
                    if (parts[0].trim().isEmpty()) {
                        throw new AtlasException("Deadline description cannot be empty.");
                    }

                    try {
                        LocalDate by = LocalDate.parse(parts[1].trim());
                        tasks.add(new Deadline(parts[0].trim(), by));
                        storage.save(tasks.getAll());

                        ui.showMessage("Got it. I've added this task:");
                        ui.showMessage("  " + tasks.get(tasks.size() - 1));
                    } catch (DateTimeParseException e) {
                        throw new AtlasException("Please use date format yyyy-mm-dd.");
                    }
                }

                else if (input.startsWith("event")) {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new AtlasException("An event needs /from and /to dates.");
                    }

                    String[] parts = input.substring(6).split("/from|/to");
                    if (parts[0].trim().isEmpty()) {
                        throw new AtlasException("Event description cannot be empty.");
                    }

                    try {
                        LocalDate from = LocalDate.parse(parts[1].trim());
                        LocalDate to = LocalDate.parse(parts[2].trim());

                        tasks.add(new Event(parts[0].trim(), from, to));
                        storage.save(tasks.getAll());

                        ui.showMessage("Got it. I've added this task:");
                        ui.showMessage("  " + tasks.get(tasks.size() - 1));
                    } catch (DateTimeParseException e) {
                        throw new AtlasException("Please use date format yyyy-mm-dd.");
                    }
                }

                else if (input.startsWith("mark")) {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new AtlasException("That task number does not exist.");
                        }
                        tasks.get(index).markDone();
                        storage.save(tasks.getAll());

                        ui.showMessage("Nice! I've marked this task as done:");
                        ui.showMessage("  " + tasks.get(index));
                    } catch (NumberFormatException e) {
                        throw new AtlasException("Please provide a valid task number.");
                    }
                }

                else if (input.startsWith("unmark")) {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new AtlasException("That task number does not exist.");
                        }
                        tasks.get(index).markUndone();
                        storage.save(tasks.getAll());

                        ui.showMessage("OK, I've marked this task as not done yet:");
                        ui.showMessage("  " + tasks.get(index));
                    } catch (NumberFormatException e) {
                        throw new AtlasException("Please provide a valid task number.");
                    }
                }

                else if (input.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new AtlasException("That task number does not exist.");
                        }

                        Task removedTask = tasks.remove(index);
                        storage.save(tasks.getAll());

                        ui.showMessage("Noted. I've removed this task:");
                        ui.showMessage("  " + removedTask);
                        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (NumberFormatException e) {
                        throw new AtlasException("Please provide a valid task number to delete.");
                    }
                }

                else {
                    throw new AtlasException("I don't know what that command means.");
                }

            } catch (AtlasException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
