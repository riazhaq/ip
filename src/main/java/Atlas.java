import java.util.Scanner;

public class Atlas {
    public static void main(String[] args) {




        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------");
        System.out.println("Instructions:");
        System.out.println("  Add a todo task:");
        System.out.println("    todo <description>");
        System.out.println();
        System.out.println("  Add a deadline:");
        System.out.println("    deadline <description> /by <date>");
        System.out.println();
        System.out.println("  Add an event:");
        System.out.println("    event <description> /from <start> /to <end>");
        System.out.println();
        System.out.println("  Other commands:");
        System.out.println("    list            → show all tasks");
        System.out.println("    mark N          → mark task N as done");
        System.out.println("    unmark N        → mark task N as not done");
        System.out.println("    bye             → exit Atlas");
        System.out.println("--------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int count = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }

            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);

            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks[count] = new Todo(description);
                count++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[count - 1]);
                System.out.println("Now you have " + count + " tasks in the list.");

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                String description = parts[0];
                String by = parts[1];
                tasks[count] = new Deadline(description, by);
                count++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[count - 1]);
                System.out.println("Now you have " + count + " tasks in the list.");

            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ", 2);
                String description = parts[0];
                String[] timeParts = parts[1].split(" /to ", 2);
                String from = timeParts[0];
                String to = timeParts[1];
                tasks[count] = new Event(description, from, to);
                count++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[count - 1]);
                System.out.println("Now you have " + count + " tasks in the list.");

            } else {
                System.out.println("Sorry, I don't understand that command.");
            }
        }
    }
}
