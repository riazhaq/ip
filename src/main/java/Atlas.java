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

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(input);
        }
    }
}


