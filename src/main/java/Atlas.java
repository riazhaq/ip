import java.util.Scanner;

public class Atlas {
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

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
                    if (taskCount == 0) {
                        System.out.println("Your task list is empty.");
                        continue;
                    }
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }

                else if (input.startsWith("todo")) {
                    if (input.length() <= 4) {
                        throw new AtlasException("A todo needs a description.");
                    }
                    String description = input.substring(5);
                    tasks[taskCount++] = new Todo(description);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount - 1]);
                }

                else if (input.startsWith("deadline")) {
                    if (!input.contains("/by")) {
                        throw new AtlasException("A deadline needs a /by time.");
                    }
                    String[] parts = input.substring(9).split("/by", 2);
                    if (parts[0].trim().isEmpty()) {
                        throw new AtlasException("Deadline description cannot be empty.");
                    }
                    tasks[taskCount++] = new Deadline(parts[0].trim(), parts[1].trim());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount - 1]);
                }

                else if (input.startsWith("event")) {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new AtlasException("An event needs /from and /to.");
                    }
                    String[] parts = input.substring(6).split("/from|/to");
                    if (parts[0].trim().isEmpty()) {
                        throw new AtlasException("Event description cannot be empty.");
                    }
                    tasks[taskCount++] = new Event(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim()
                    );
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount - 1]);
                }

                else if (input.startsWith("mark")) {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if (index < 0 || index >= taskCount) {
                            throw new AtlasException("That task number does not exist.");
                        }
                        tasks[index].markDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks[index]);
                    } catch (NumberFormatException e) {
                        throw new AtlasException("Please provide a valid task number.");
                    }
                }

                else if (input.startsWith("unmark")) {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= taskCount) {
                            throw new AtlasException("That task number does not exist.");
                        }
                        tasks[index].markUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks[index]);
                    } catch (NumberFormatException e) {
                        throw new AtlasException("Please provide a valid task number.");
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
