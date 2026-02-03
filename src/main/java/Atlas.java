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

        Scanner scanner = new Scanner(System.in);

        String[] items = new String[100];
        int count = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + items[i]);
                }
            } else {
                items[count] = input;
                count++;
                System.out.println("added: " + input);
            }
        }
    }
}



