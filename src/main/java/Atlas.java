import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Atlas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/atlas.txt");
        ArrayList<Task> tasks;

        // Load tasks on startup
        try {
            tasks = storage.load();
        } catch (AtlasException e) {
            System.out.println("Starting with an empty task list.");
            tasks = new ArrayList<>();
        }

        System.out.println("Hello! I'm Atlas.");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String input = scanner.nextLine().trim();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                else if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println("Your task list is empty.");
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }

                else if (input.startsWith("todo")) {
                    if (input.length() <= 4) {
                        throw new AtlasException("A todo needs a description.");
                    }
                    String description = input.substring(5);
                    tasks.add(new Todo(description));
                    storage.save(tasks);

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
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
                        storage.save(tasks);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
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
                        storage.save(tasks);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
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
                        storage.save(tasks);

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(index));
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
                        storage.save(tasks);

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(index));
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
                        storage.save(tasks);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (NumberFormatException e) {
                        throw new AtlasException("Please provide a valid task number to delete.");
                    }
                }

                else {
                    throw new AtlasException("I don't know what that command means.");
                }

            } catch (AtlasException e) {
                System.out.println("--------------------------------------------------");
                System.out.println("Oops! " + e.getMessage());
                System.out.println("--------------------------------------------------");
            }
        }

        scanner.close();
    }
}
