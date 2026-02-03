import java.util.Scanner;

public class Atlas {
    public static void main(String[] args) {
        String logo =
                "  █████╗ ████████╗██╗      █████╗ ███████╗\n"
                        + " ██╔══██╗╚══██╔══╝██║     ██╔══██╗██╔════╝\n"
                        + " ███████║   ██║   ██║     ███████║███████╗\n"
                        + " ██╔══██║   ██║   ██║     ██╔══██║╚════██║\n"
                        + " ██║  ██║   ██║   ███████╗██║  ██║███████║\n"
                        + " ╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝\n";

        System.out.println("Hello! I'm Atlas");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------");
        System.out.println("Instructions:");
        System.out.println(" - Type any text to add a task");
        System.out.println(" - Type 'list' to see all tasks");
        System.out.println(" - Type 'mark N' to mark task N as done");
        System.out.println(" - Type 'unmark N' to mark task N as not done");
        System.out.println(" - Type 'bye' to exit");
        System.out.println("----------------------------------------");

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

            } else {
                tasks[count] = new Task(input);
                System.out.println("added: " + input);
                count++;
            }
        }
    }
}


