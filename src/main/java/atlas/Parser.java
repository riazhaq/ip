package atlas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses raw user input into structured commands.
 */
public class Parser {

    /**
     * Parses the user input into a {@code ParsedCommand}.
     *
     * @param input the raw user input
     * @return a parsed representation of the command
     * @throws AtlasException if the command is invalid
     */
    public static ParsedCommand parse(String input) throws AtlasException {
        input = input.trim();

        if (input.equals("bye")) {
            return new ParsedCommand(CommandType.BYE);
        }

        if (input.equals("list")) {
            return new ParsedCommand(CommandType.LIST);
        }

        if (input.startsWith("todo")) {
            if (input.length() <= 4) {
                throw new AtlasException("A todo needs a description.");
            }
            return new ParsedCommand(CommandType.TODO, input.substring(5));
        }

        if (input.startsWith("deadline")) {
            if (!input.contains("/by")) {
                throw new AtlasException("A deadline needs a /by date.");
            }

            String[] parts = input.substring(9).split("/by", 2);
            if (parts[0].trim().isEmpty()) {
                throw new AtlasException("Deadline description cannot be empty.");
            }

            try {
                LocalDate by = LocalDate.parse(parts[1].trim());
                return new ParsedCommand(CommandType.DEADLINE, parts[0].trim(), by);
            } catch (DateTimeParseException e) {
                throw new AtlasException("Please use date format yyyy-mm-dd.");
            }
        }

        if (input.startsWith("event")) {
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
                return new ParsedCommand(CommandType.EVENT, parts[0].trim(), from, to);
            } catch (DateTimeParseException e) {
                throw new AtlasException("Please use date format yyyy-mm-dd.");
            }
        }

        if (input.startsWith("mark")) {
            return new ParsedCommand(CommandType.MARK, parseIndex(input, 5));
        }

        if (input.startsWith("unmark")) {
            return new ParsedCommand(CommandType.UNMARK, parseIndex(input, 7));
        }

        if (input.startsWith("delete")) {
            return new ParsedCommand(CommandType.DELETE, parseIndex(input, 7));
        }

        throw new AtlasException("I don't know what that command means.");
    }

    private static int parseIndex(String input, int start) throws AtlasException {
        try {
            return Integer.parseInt(input.substring(start).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AtlasException("Please provide a valid task number.");
        }
    }
}

